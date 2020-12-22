package com.irs.register.register.infra.messaging;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.register.register.application.controller.taxpayer.TaxpayerDTO;

@Configuration
public class MessagingAdapterTaxPayer implements MessagingPort<TaxPayer> {
	
	@Autowired
	private KafkaProperties kafkaProperties;

	@Override
	public Producer<String, TaxPayer> configureProducer() {

		Properties properties = new Properties();
		
        properties.put(BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        properties.put(ACKS_CONFIG, kafkaProperties.getAcksConfig());
        properties.put(RETRIES_CONFIG, kafkaProperties.getRetriesConfig());
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getValueSerializer());
        properties.put(SCHEMA_REGISTRY_URL_CONFIG, kafkaProperties.getSchemaRegistryUrl());

		
		return new KafkaProducer<String, TaxPayer>(properties);
		
	}

	@Override
	public String getTopic() {
		return "taxpayer";
	}

	@Bean(name = "taxpayerProducer")
	@Override
	public ProducerRecord<String, TaxPayer> createProducerRecord(
			com.irs.register.register.shared.dto.TaxPayer taxpayerDTO) {

		TaxPayer taxPayer = TaxPayer.newBuilder().setName(((TaxpayerDTO) taxpayerDTO).getName())
				.setDocument(((TaxpayerDTO) taxpayerDTO).getDocument()).setSituation(false).build();

		return new ProducerRecord<String, TaxPayer>(this.getTopic(), taxPayer);
		
	}

}
