package com.irs.register.register.infra.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerRecord;

import com.irs.register.avro.taxpayer.TaxPayer;
import com.irs.register.register.application.controller.taxpayer.TaxpayerDTO;

public class MessagingAdapterTaxPayer implements MessagingPort<TaxPayer> {

	@Override
	public Properties configure() {

		Properties properties = new Properties();

		Map<String, String> props = new HashMap<>();

		props.forEach((key, value) -> properties.setProperty(key, value));

		return properties;
	}

	@Override
	public String getTopic() {
		return "taxpayer";
	}

	@Override
	public ProducerRecord<String, TaxPayer> createProducerRecord(
			com.irs.register.register.shared.dto.TaxPayer taxpayerDTO) {

		TaxPayer taxPayer = TaxPayer.newBuilder().setName(((TaxpayerDTO) taxpayerDTO).getName())
				.setDocument(((TaxpayerDTO) taxpayerDTO).getDocument()).setSituation(false).build();

		return new ProducerRecord<String, TaxPayer>(this.getTopic(), taxPayer);
	}

}
