package com.demo.fileupload.web.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class DublicateNameFoundException extends BadRequestException {

    public DublicateNameFoundException(String message) {
        super(message);
    }

    public DublicateNameFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
