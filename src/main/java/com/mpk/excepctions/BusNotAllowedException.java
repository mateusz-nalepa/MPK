package com.mpk.excepctions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusNotAllowedException extends Exception {
    public BusNotAllowedException(String error) {
        super(error);
    }
}
