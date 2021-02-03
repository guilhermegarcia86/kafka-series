package com.irs.decider.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irs.decider.entity.TaxpayerEntity;

@Component
public class TaxpayerAdapter implements TaxpayerPort{
	
	@Autowired
	private TaxpayerRepository repository;

	@Override
	public TaxpayerEntity save(TaxpayerEntity taxpayer) {
		return repository.save(taxpayer);
	}

}
