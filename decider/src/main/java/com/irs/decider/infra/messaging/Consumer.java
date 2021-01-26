package com.irs.decider.infra.messaging;

import org.apache.avro.specific.SpecificRecordBase;

public interface Consumer<T extends SpecificRecordBase> {
	
	String getTopic();
	
	void start();

}
