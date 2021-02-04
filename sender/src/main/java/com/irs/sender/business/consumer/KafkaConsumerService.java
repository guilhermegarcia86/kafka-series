package com.irs.sender.business.consumer;

import java.time.Duration;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.sender.domain.Person;
import com.irs.sender.infra.mail.Email;
import com.irs.sender.infra.messaging.MessageConsumer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService implements MessageConsumer<TaxPayer> {

	private final Consumer<String, TaxPayer> kafkaConsumer;

	private final Email email;

	@Autowired
	public KafkaConsumerService(@Qualifier("taxpayerConsumer") Consumer<String, TaxPayer> kafkaConsumer, Email email) {
		this.kafkaConsumer = kafkaConsumer;
		this.email = email;
	}

	@Override
	public String topic() {
		return "taxpayer-avro";
	}

	@PostConstruct
	@Override
	public void receive() {

		Consumer<String, TaxPayer> consumer = kafkaConsumer;

		consumer.subscribe(Collections.singleton(this.topic()));

		while (true) {

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
				break;
			}

		}

	}

	public void close() {
		kafkaConsumer.close();
	}

}
