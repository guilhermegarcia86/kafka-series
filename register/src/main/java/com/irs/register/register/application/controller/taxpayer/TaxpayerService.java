package com.irs.register.register.application.controller.taxpayer;


import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.register.register.infra.messaging.MessagingPort;
import com.irs.register.register.shared.dto.CommonDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class TaxpayerService implements MessagingPort<TaxPayer> {

	private final Producer<String, TaxPayer> producer;

	@Override
	public String topic() {
		return "taxpayer-avro";
	}

	@Autowired
	public TaxpayerService(@Qualifier("taxpayerProducer") Producer<String, TaxPayer> producer) {
		this.producer = producer;
	}

	@Override
	public ProducerRecord<String, TaxPayer> createProducerRecord(TaxPayer taxPayer) {

		return new ProducerRecord<>(this.topic(), taxPayer);
		
	}

	@Override
	public void send(CommonDTO taxpayerDTO) {
		
		Random rd = new Random();

		TaxPayer taxPayer = TaxPayer.newBuilder().setName(((TaxpayerDTO) taxpayerDTO).getName())
				.setDocument(((TaxpayerDTO) taxpayerDTO).getDocument()).setSituation(rd.nextBoolean()).setEmail(((TaxpayerDTO) taxpayerDTO).getEmail()).build();
		
		
		if(taxPayer.getName().contains("Guilherme")) {
			throw new BadTaxpayerUser(taxPayer.getName());
		}
		
		
		producer.send(this.createProducerRecord(taxPayer), (rm, ex) -> {
			if (ex == null) {
				log.info("Data sent with success!!!");
			} else {
				log.error("Fail to send message", ex);
			}
		});

		producer.flush();

	}

}
