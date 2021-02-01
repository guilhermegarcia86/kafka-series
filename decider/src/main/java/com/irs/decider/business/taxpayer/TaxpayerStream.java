package com.irs.decider.business.taxpayer;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irs.decider.infra.messaging.MessageConfiguration;
import com.irs.decider.infra.messaging.MessageStream;
import com.irs.register.avro.taxpayer.TaxPayer;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

@Component
public class TaxpayerStream implements MessageStream {
	
	@Autowired
	private MessageConfiguration kakfaConfiguration;
	
	@Autowired
	private TaxpayerProcessorSituationTrue processorTrue;
	
	@Autowired
	private TaxpayerProcessorSituationFalse processorFalse;

	private KafkaStreams kafkaStreams;

	@Override
	public String getTopic() {
		return "taxpayer-avro";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public StreamsBuilder creataStream() {
		
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		
		Serde<TaxPayer> taxpayerAvroSerde = new SpecificAvroSerde<>();
		
		taxpayerAvroSerde.configure(getSerdeProperties(), false);
		
		KStream<String, TaxPayer> stream = streamsBuilder.stream(getTopic(), Consumed.with(Serdes.String(), taxpayerAvroSerde));
		
		KStream<String, TaxPayer>[] branch = stream.branch(
				(id, tax) -> tax.getSituation() == false,
				(id, tax) -> tax.getSituation() == true
				);
		
		branch[0].process(() -> processorFalse);
		branch[1].process(() -> processorTrue);
		
		return streamsBuilder;
	}

	@PostConstruct
	@Override
	public void start() {
		
		StreamsBuilder streamsBuilder = this.creataStream();
		
		kafkaStreams = new KafkaStreams(streamsBuilder.build(), kakfaConfiguration.configureProperties());
		kafkaStreams.setUncaughtExceptionHandler(this.getUncaughtExceptionHandler());
        kafkaStreams.start();
		
        this.shutDown();
	}

	@Override
	public void shutDown() {
		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
	}
	
    private Map<String, String> getSerdeProperties() {
        return Collections.singletonMap(SCHEMA_REGISTRY_URL_CONFIG, (String)kakfaConfiguration.configureProperties().get(SCHEMA_REGISTRY_URL_CONFIG));
    }
    
    private UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return (thread, exception) -> exception.printStackTrace();
    }

}
