package com.irs.sender.infra.messaging;

import org.apache.avro.specific.SpecificRecordBase;

public interface MessageConsumer<T extends SpecificRecordBase> {
	
	String topic();
	
	void receive();
	
	void close();
	
}
