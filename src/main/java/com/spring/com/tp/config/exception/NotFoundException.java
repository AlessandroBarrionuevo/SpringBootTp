package com.spring.com.tp.config.exception;

import java.util.function.Supplier;

public class NotFoundException extends RuntimeException {
    public NotFoundException(){}

    public NotFoundException(String message){
        super(message);
    }


}
