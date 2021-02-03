package com.irs.decider.infra.messaging;

import java.util.List;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
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
	private String clientId;
	private String groupId;
	private String processingGuaranteeConfig;
	private String offsetReset;
	private String defaultKeySerde = Serdes.String().getClass().getName();
	private Class<?> defaultValueSerde = SpecificAvroSerde.class;
	private String schemaRegistryUrl;
	private boolean specficAvroReader;
	private String timeStampExtarctor = WallclockTimestampExtractor.class.getName();

}
