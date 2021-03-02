package com.irs.drainer.usecase.port;

import com.irs.drainer.domain.Taxpayer;

import java.util.List;
import java.util.Optional;

public interface TaxpayerRepository {

    Optional<Taxpayer> findByEmail(String email);

    Optional<Taxpayer> findByDocument(String document);

    Optional<List<Taxpayer>> findAll();
}
