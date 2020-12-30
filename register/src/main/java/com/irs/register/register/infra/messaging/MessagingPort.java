package com.irs.register.register.infra.messaging;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.irs.register.register.shared.dto.CommonDTO;

public interface MessagingPort<T extends SpecificRecordBase> {
	
	String topic();
	
	ProducerRecord<String, T> createProducerRecord(T type);
	
	void send(CommonDTO taxpayerDTO);
}
