package com.irs.decider.infra.messaging;

import java.util.List;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
	private String applicationId;
	private String processingGuaranteeConfig;
	private String offsetReset;
	private Class<?> defaultKeySerde = Serdes.String().getClass();
	private Class<?> defaultValueSerde = Serdes.String().getClass();
	private String schemaRegistryUrl;

}
