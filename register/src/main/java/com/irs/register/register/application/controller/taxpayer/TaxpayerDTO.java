package com.irs.register.register.application.controller.taxpayer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.irs.register.register.application.controller.taxpayer.validation.Document;
import com.irs.register.register.shared.dto.CommonDTO;

import lombok.Data;

@Data
public class TaxpayerDTO implements CommonDTO{
	
	@NotBlank
	@Min(value = 3)
	private String name;
	
	@Document
	private String document;

	@Override
	public String getType() {
		return "TaxPayerDTO";
	}

}
