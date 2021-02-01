package com.irs.decider.repository;

import com.irs.decider.entity.TaxpayerEntity;

public interface TaxpayerPort {
	
	TaxpayerEntity save(TaxpayerEntity taxpayer);

}
