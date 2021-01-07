package com.irs.sender.infra.messaging;

import java.util.List;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(
    prefix = "kafka"
)
@Data
@NoArgsConstructor
public class KafkaProperties {
	
	private List<String> bootstrapServers;
	private String groupId;
	private String autoCommit;
	private String offsetReset;
	private Class<?> keyDesserializer = StringDeserializer.class;
	private Class<?> valueDesserializer = KafkaAvroDeserializer.class;
	private String schemaRegistryUrl;
	private boolean specificAvroReader;

}
