package com.irs.sender.business.consumer;

import java.time.Duration;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.sender.domain.Person;
import com.irs.sender.infra.mail.Email;
import com.irs.sender.infra.messaging.MessageConsumer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService implements MessageConsumer<TaxPayer> {

    @Value("${cronTimer.process}")
    private String process;

    private final Consumer<String, TaxPayer> kafkaConsumer;

    private final Email email;

    private final ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    public KafkaConsumerService(@Qualifier("taxpayerConsumer") Consumer<String, TaxPayer> kafkaConsumer, Email email, ThreadPoolTaskScheduler taskScheduler) {
        this.kafkaConsumer = kafkaConsumer;
        this.email = email;
        this.taskScheduler = taskScheduler;
    }

    @PostConstruct
    public void init() {
        taskScheduler.schedule(() -> {
            this.receive();
        }, new CronTrigger(process));
    }

    @Override
    public String topic() {
        return "taxpayer-avro";
    }

    @Override
    public void receive() {

        Consumer<String, TaxPayer> consumer = kafkaConsumer;

        consumer.subscribe(Collections.singleton(this.topic()));

        try {

            consumer.poll(Duration.ofMillis(1000)).forEach(record -> {

                log.info("Recebendo TaxPayer");

                TaxPayer taxpayer = record.value();

                Person person = Person.builder().email(taxpayer.getEmail()).name(taxpayer.getName()).build();

                email.sendMessage(person);

            });

            consumer.commitSync();

        } catch (Exception ex) {
            log.error("Erro ao processar mensagem", ex);
        }

    }
}
