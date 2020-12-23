package com.irs.register.register.infra.messaging.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;

public interface MessagingConfigPort<T extends SpecificRecordBase> {
	
	Producer<String, T> configureProducer();
	
}
