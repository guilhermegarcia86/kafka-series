package com.irs.sender.business.consumer;

import java.time.Duration;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.sender.domain.Person;
import com.irs.sender.infra.mail.Email;
import com.irs.sender.infra.messaging.Consumer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService implements Consumer<TaxPayer> {

	@Autowired
	@Qualifier("taxpayerConsumer")
	private KafkaConsumer<String, TaxPayer> kafkaConsumer;

	@Autowired
	private Email email;

	@Override
	public String topic() {
		return "taxpayer-avro";
	}

	@PostConstruct
	@Override
	public void receive() {

		kafkaConsumer.subscribe(Collections.singleton(this.topic()));

		while (true) {

			try {

				ConsumerRecords<String, TaxPayer> records = kafkaConsumer.poll(Duration.ofMillis(1000));

				for (ConsumerRecord<String, TaxPayer> record : records) {
					
					log.info("Recebendo TaxPayer");
					
					TaxPayer taxpayer = record.value();

					Person person = Person.builder().email(taxpayer.getEmail()).name(taxpayer.getName()).build();
					
					email.sendMessage(person);
				}

				kafkaConsumer.commitSync();

			} catch (Exception ex) {
				log.error("Erro ao processar mensagem", ex);
			}

		}

	}

}
