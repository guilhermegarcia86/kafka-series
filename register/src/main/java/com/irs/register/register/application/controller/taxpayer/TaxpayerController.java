package com.irs.register.register.application.controller.taxpayer;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taxpayer")
public class TaxpayerController {
	
	@PostMapping
	public ResponseEntity<TaxpayerDTO> postTaxpayer(@RequestBody @Validated TaxpayerDTO taxpayer){
		return null;
	}

}
