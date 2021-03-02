package com.irs.drainer.adapter.repository;

import com.irs.drainer.adapter.repository.entity.After;
import com.irs.drainer.adapter.repository.entity.TaxpayerEntity;
import com.irs.drainer.domain.Taxpayer;
import com.irs.drainer.usecase.port.TaxpayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MongoTaxpayer implements TaxpayerRepository {

    private final MongoTaxpayerRepository mongoTaxpayerRepository;

    @Autowired
    public MongoTaxpayer(MongoTaxpayerRepository mongoTaxpayerRepository){
        this.mongoTaxpayerRepository = mongoTaxpayerRepository;
    }

    @Override
    public Optional<Taxpayer> findByEmail(String email) {

        final Optional<TaxpayerEntity> optionalTaxpayerEntity = mongoTaxpayerRepository.findByAfter_Email(email);

        if(!optionalTaxpayerEntity.isPresent()){
            return Optional.empty();
        }

        final After after = optionalTaxpayerEntity.get().getAfter();

        return Optional.of(Taxpayer.builder().name(after.getName()).document(after.getDocument()).email(after.getEmail()).status(after.getStatus()).build());
    }

    @Override
    public Optional<Taxpayer> findByDocument(String document) {

        final Optional<TaxpayerEntity> optionalTaxpayerEntity = mongoTaxpayerRepository.findByAfter_Document(document);

        if(!optionalTaxpayerEntity.isPresent()){
            return Optional.empty();
        }

        final After after = optionalTaxpayerEntity.get().getAfter();

        return Optional.of(Taxpayer.builder().name(after.getName()).document(after.getDocument()).email(after.getEmail()).status(after.getStatus()).build());
    }

    @Override
    public Optional<List<Taxpayer>> findAll() {

        final List<TaxpayerEntity> taxpayerEntityList = mongoTaxpayerRepository.findAll();

        if(null == taxpayerEntityList || taxpayerEntityList.isEmpty()){
            return Optional.empty();
        }

        final List<Taxpayer> taxpayerList = taxpayerEntityList.stream().map(taxpayerEntity -> {
            final After after = taxpayerEntity.getAfter();

            return Taxpayer.builder().name(after.getName()).document(after.getDocument()).email(after.getEmail()).status(after.getStatus()).build();
        }).collect(Collectors.toList());

        return Optional.of(taxpayerList);
    }
}
