package com.demo.fileupload.web.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class NameAlreadyAvailableException extends BadRequestException {
    public NameAlreadyAvailableException(String message) {
        super(message);
    }

    public NameAlreadyAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
