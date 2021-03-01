package com.irs.drainer.adapter.repository;

import com.irs.drainer.domain.Taxpayer;
import com.irs.drainer.usecase.port.TaxpayerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryDB  implements TaxpayerRepository {

    private final Map<String, Taxpayer> inMemoryTaxpayer = new HashMap<>();

    public InMemoryDB(){
        final Taxpayer taxpayer = Taxpayer.builder().document("11111111111").name("John").status("STATUS").email("fake@fake.com").build();
        inMemoryTaxpayer.put(taxpayer.getDocument(), taxpayer);
    }

    @Override
    public Optional<Taxpayer> findByEmail(String email) {
        return inMemoryTaxpayer.values().stream().filter(taxpayer -> taxpayer.getEmail().equals(email)).findAny();
    }

    @Override
    public Optional<Taxpayer> findByDocument(String document) {
        return inMemoryTaxpayer.values().stream().filter(taxpayer -> taxpayer.getDocument().equals(document)).findAny();
    }

    @Override
    public Optional<List<Taxpayer>> findAll() {
        return Optional.of(inMemoryTaxpayer.values().stream().collect(Collectors.toList()));
    }
}
