package com.irs.decider.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.irs.register.avro.taxpayer.TaxPayer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DefaultedTaxpayer")
@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultedTaxpayer extends TaxpayerEntity {

	@Enumerated(EnumType.STRING)
	private Status status;
	
	public static DefaultedTaxpayer createDefaultedTaxpayer(TaxPayer taxPayer) {
		DefaultedTaxpayer defaultedTaxpayer = new DefaultedTaxpayer();
		defaultedTaxpayer.setName(taxPayer.getName());
		defaultedTaxpayer.setEmail(taxPayer.getEmail());
		defaultedTaxpayer.setDocument(taxPayer.getDocument());
		defaultedTaxpayer.setStatus(Status.DEFAULTED);
		
		return defaultedTaxpayer;
	}
}
