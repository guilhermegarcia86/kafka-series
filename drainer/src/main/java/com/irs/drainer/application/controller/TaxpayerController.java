package com.irs.drainer.application.controller;

import com.irs.drainer.domain.Taxpayer;
import com.irs.drainer.usecase.SearchTaxpayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taxpayer")
public class TaxpayerController {

    @Autowired
    private SearchTaxpayer searchTaxpayer;

    @GetMapping
    public ResponseEntity<List<Taxpayer>> getAllTaxpayers(){
        return ResponseEntity.ok(searchTaxpayer.findAll());
    }

    @GetMapping("document")
    public ResponseEntity<Taxpayer> getTaxpayerByDocument(@RequestParam("document") String document){
        return ResponseEntity.ok(searchTaxpayer.searchByDocument(document));
    }

    @GetMapping("email")
    public ResponseEntity<Taxpayer> getTaxpayerByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(searchTaxpayer.searchByEmail(email));
    }

}
