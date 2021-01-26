package com.irs.register.register.application.controller.taxpayer;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import me.alidg.errors.annotation.ExceptionMapping;
import me.alidg.errors.annotation.ExposeAsArg;

@Getter
@ExceptionMapping(statusCode = HttpStatus.I_AM_A_TEAPOT, errorCode = "bad.user.message")
public class BadTaxpayerUser extends RuntimeException {
	
    @ExposeAsArg(value = 0, name = "user")
    private final String key;

    public BadTaxpayerUser(String key) {
        super(key);
        this.key = key;
    }

}
