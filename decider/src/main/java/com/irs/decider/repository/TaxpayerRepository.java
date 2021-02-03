package com.irs.decider.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irs.decider.entity.TaxpayerEntity;

@Repository
public interface TaxpayerRepository extends CrudRepository<TaxpayerEntity, String> {

}
