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
@Table(name = "ComplaintTaxpayer")
@Data
@EqualsAndHashCode(callSuper = true)
public class ComplaintTaxpayer extends TaxpayerEntity {
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public static ComplaintTaxpayer createDefaultedTaxpayer(TaxPayer taxPayer) {
		ComplaintTaxpayer complaintTaxpayer = new ComplaintTaxpayer();
		complaintTaxpayer.setName(taxPayer.getName());
		complaintTaxpayer.setEmail(taxPayer.getEmail());
		complaintTaxpayer.setDocument(taxPayer.getDocument());
		complaintTaxpayer.setStatus(Status.COMPLIANT);
		
		return complaintTaxpayer;
	}

}
