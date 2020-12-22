package com.irs.register.register.infra.messaging;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.irs.register.register.shared.dto.TaxPayer;

public interface MessagingPort<T extends SpecificRecordBase> {
	
	Producer<String, T> configureProducer();
	
	String getTopic();
	
	ProducerRecord<String, T> createProducerRecord(TaxPayer taxPayer);

}
