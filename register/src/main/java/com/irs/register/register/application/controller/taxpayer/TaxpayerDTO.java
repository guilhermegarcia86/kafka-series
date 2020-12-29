package com.irs.register.register.application.controller.taxpayer;

import com.irs.register.register.shared.dto.CommonDTO;

import lombok.Data;

@Data
public class TaxpayerDTO implements CommonDTO{
	
	private String name;
	
	private String document;

	@Override
	public String getType() {
		return "TaxPayerDTO";
	}

}
