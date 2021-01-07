package com.irs.sender.infra.messaging;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irs.register.avro.taxpayer.TaxPayer;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
public class KafkaConfiguration implements MessageConfiguration<TaxPayer> {
	
	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean(name = "taxpayerConsumer")
	@Override
	public KafkaConsumer<String, TaxPayer> configureConsumer() {
		
		Properties props = new Properties();
		
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getGroupId());
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaProperties.getAutoCommit());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getOffsetReset());
		
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getKeyDesserializer());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getValueDesserializer());
		
		props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getSchemaRegistryUrl());
		props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, kafkaProperties.isSpecificAvroReader());
		
		return new KafkaConsumer<String, TaxPayer>(props);
	}

}
