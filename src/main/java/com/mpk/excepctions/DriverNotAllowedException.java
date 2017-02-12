package com.mpk.excepctions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DriverNotAllowedException extends Exception {
    public DriverNotAllowedException(String error) {
        super(error);
    }
}