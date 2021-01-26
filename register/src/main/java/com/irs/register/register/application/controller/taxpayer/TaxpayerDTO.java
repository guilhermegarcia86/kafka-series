package com.irs.register.register.application.controller.taxpayer;

import com.irs.register.register.application.controller.validation.Cpf;
import com.irs.register.register.shared.dto.CommonDTO;

import lombok.Data;

@Data
public class TaxpayerDTO implements CommonDTO{
	
	private String name;
	
	@Cpf
	private String document;
	
	private String email;

	@Override
	public String getType() {
		return "TaxPayerDTO";
	}

}
