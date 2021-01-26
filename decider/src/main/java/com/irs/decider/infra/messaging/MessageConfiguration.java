package com.irs.decider.infra.messaging;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.consumer.Consumer;

public interface MessageConfiguration<T extends SpecificRecordBase> {

	Consumer<String, T> configureConsumer();
}
