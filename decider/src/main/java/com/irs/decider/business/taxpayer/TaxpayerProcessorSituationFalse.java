package com.irs.decider.business.taxpayer;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irs.decider.entity.DefaultedTaxpayer;
import com.irs.decider.repository.TaxpayerPort;
import com.irs.register.avro.taxpayer.TaxPayer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaxpayerProcessorSituationFalse extends AbstractProcessor<String, TaxPayer> {

	private TaxpayerPort repository;

	@Autowired
	public TaxpayerProcessorSituationFalse(TaxpayerPort repository) {
		this.repository = repository;
	}

	@Override
	public void process(String key, TaxPayer value) {
		log.info("Processing Taxpayer with situation :: " + value.getSituation());
		DefaultedTaxpayer defaultedTaxpayer = DefaultedTaxpayer.createDefaultedTaxpayer(value);
		repository.save(defaultedTaxpayer);

	}

}
