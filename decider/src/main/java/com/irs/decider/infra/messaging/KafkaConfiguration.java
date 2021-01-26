package com.irs.decider.infra.messaging;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irs.register.avro.taxpayer.TaxPayer;

import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
public class KafkaConfiguration implements MessageConfiguration<TaxPayer> {
	
	@Autowired
	private KafkaProperties kafkaProperties;

	@Bean(name = "taxpayerStream")
	@Override
	public KafkaConsumer<String, TaxPayer> configureConsumer() {
		
		Properties props = new Properties();
		
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, kafkaProperties.getApplicationId());
		props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, kafkaProperties.getProcessingGuaranteeConfig());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaProperties.getOffsetReset());
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, kafkaProperties.getDefaultKeySerde());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, kafkaProperties.getDefaultValueSerde());
		props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getSchemaRegistryUrl());
		
		return new KafkaConsumer<String, TaxPayer>(props);
	}

}
