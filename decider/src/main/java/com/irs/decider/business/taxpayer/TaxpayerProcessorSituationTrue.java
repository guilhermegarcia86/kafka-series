package com.irs.decider.business.taxpayer;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irs.decider.entity.ComplaintTaxpayer;
import com.irs.decider.repository.TaxpayerPort;
import com.irs.register.avro.taxpayer.TaxPayer;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TaxpayerProcessorSituationTrue extends AbstractProcessor<String, TaxPayer>{
	
	@Autowired
	private TaxpayerPort repository;

	@Override
	public void process(String key, TaxPayer value) {
		log.info("Processing Taxpayer with situation :: " + value.getSituation());
		ComplaintTaxpayer complaintTaxpayer = ComplaintTaxpayer.createDefaultedTaxpayer(value);
		repository.save(complaintTaxpayer);
		
	}

}
