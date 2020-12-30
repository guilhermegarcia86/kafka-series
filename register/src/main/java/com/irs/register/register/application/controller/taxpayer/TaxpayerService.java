package com.irs.register.register.application.controller.taxpayer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.register.register.infra.messaging.MessagingPort;
import com.irs.register.register.shared.dto.CommonDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaxpayerService implements MessagingPort<TaxPayer> {

	@Autowired
	@Qualifier("taxpayerProducer")
	private KafkaProducer<String, TaxPayer> producer;

	@Override
	public String topic() {
		return "taxpayer-avro";
	}
		
	@Override
	public ProducerRecord<String, TaxPayer> createProducerRecord(TaxPayer taxPayer) {

		return new ProducerRecord<String, TaxPayer>(this.topic(), taxPayer);
		
	}

	@Override
	public void send(CommonDTO taxpayerDTO) {
		

		TaxPayer taxPayer = TaxPayer.newBuilder().setName(((TaxpayerDTO) taxpayerDTO).getName())
				.setDocument(((TaxpayerDTO) taxpayerDTO).getDocument()).setSituation(false).build();
		
		
		producer.send(this.createProducerRecord(taxPayer), (rm, ex) -> {
			if (ex == null) {
				log.info("Data sent with success!!!");
			} else {
				log.error("Fail to send message", ex);
			}
		});

		producer.flush();
		producer.close();

	}

}
