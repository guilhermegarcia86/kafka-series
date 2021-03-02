package com.irs.drainer.adapter.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "taxpayer")
public class TaxpayerEntity {

    @Id
    private String id;

    private After after;
}
