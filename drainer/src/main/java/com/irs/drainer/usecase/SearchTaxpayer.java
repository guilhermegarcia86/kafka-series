package com.irs.drainer.usecase;

import com.irs.drainer.domain.Taxpayer;
import com.irs.drainer.usecase.exception.TaxpayerNotFoundException;
import com.irs.drainer.usecase.port.TaxpayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SearchTaxpayer {

    private final TaxpayerRepository taxpayerRepository;

    @Autowired
    public SearchTaxpayer(TaxpayerRepository taxpayerRepository){
        this.taxpayerRepository = taxpayerRepository;
    }

    public Taxpayer searchByEmail(String email){
        final Optional<Taxpayer> optionalTaxpayer = taxpayerRepository.findByEmail(email);

        if(!optionalTaxpayer.isPresent()){
            throw new TaxpayerNotFoundException("Taxpayer not found");
        }

        return optionalTaxpayer.get();
    }

    public Taxpayer searchByDocument(String document){
        final Optional<Taxpayer> optionalTaxpayer = taxpayerRepository.findByDocument(document);

        if(!optionalTaxpayer.isPresent()){
            throw new TaxpayerNotFoundException("Taxpayer not found");
        }

        return optionalTaxpayer.get();
    }

    public List<Taxpayer> findAll(){
        return taxpayerRepository.findAll().get();
    }
}
