package com.irs.drainer.usecase.exception;

public class TaxpayerNotFoundException extends RuntimeException{

    public TaxpayerNotFoundException(String msg){
        super(msg);
    }
}
