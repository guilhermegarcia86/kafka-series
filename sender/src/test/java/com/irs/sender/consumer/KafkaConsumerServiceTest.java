package com.irs.sender.consumer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.MockConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.sender.business.consumer.KafkaConsumerService;
import com.irs.sender.infra.mail.Email;

public class KafkaConsumerServiceTest {
	
	private MockConsumer<String, TaxPayer> consumer;
	
	@Spy
	private KafkaConsumerService service;
	
	private Email email;
	
	private static final String TOPIC = "taxpayer-avro";
	
	@BeforeEach
	void prepareConsumer() {
		consumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST);
		this.prepareEmailMock();
		
		consumer.schedulePollTask(() -> {
			consumer.rebalance(Collections.singletonList(new TopicPartition(TOPIC, 0)));
			consumer.addRecord(new ConsumerRecord<String, TaxPayer>("taxpayer-avro", 0, 0l, "key", this.prepareTaxpayerMock()));
		});
		
		HashMap<TopicPartition, Long> beginningOffsets = new HashMap<>();
		beginningOffsets.put(new TopicPartition(TOPIC, 0), 0l);
		consumer.updateBeginningOffsets(beginningOffsets);
		
		consumer.subscribe(Collections.singleton("taxpayer-avro"));
		service = new KafkaConsumerService(consumer, email);
		
	}
	
	void prepareEmailMock() {
		email = person -> System.out.println("Mandando mensagem para pessoa :: " + person);
	}
	
	
	TaxPayer prepareTaxpayerMock() {
		return new TaxPayer("Guilherme", "11122233344", "meuemail@email.com", true);
	}
	
	
	@Test
	void testConsumer() {
		
		int size = consumer.poll(Duration.ofMillis(1000)).partitions().size();

		System.out.println(size);
		service.receive();
		System.out.println("Passou");
		
		verify(service, times(1)).receive();
		
	}

}
