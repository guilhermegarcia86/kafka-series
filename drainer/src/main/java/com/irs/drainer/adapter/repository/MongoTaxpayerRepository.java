package com.irs.drainer.adapter.repository;

import com.irs.drainer.adapter.repository.entity.TaxpayerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MongoTaxpayerRepository extends MongoRepository<TaxpayerEntity, String> {

    Optional<TaxpayerEntity> findByAfter_Email(String email);

    Optional<TaxpayerEntity> findByAfter_Document(String document);
}
