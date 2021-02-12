package com.irs.decider.infra.messaging;

import org.apache.kafka.streams.StreamsBuilder;

public interface MessageStream {
	
	String getTopic();
	
	StreamsBuilder createStream();
	
	void start();
	
	void shutDown();
	
}
