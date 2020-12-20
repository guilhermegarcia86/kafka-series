package com.irs.register.register.infra.messaging;

import java.util.Properties;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.irs.register.register.shared.dto.TaxPayer;

public interface MessagingPort<T extends SpecificRecordBase> {
	
	Properties configure();
	
	String getTopic();
	
	ProducerRecord<String, T> createProducerRecord(TaxPayer taxPayer);

	

}
