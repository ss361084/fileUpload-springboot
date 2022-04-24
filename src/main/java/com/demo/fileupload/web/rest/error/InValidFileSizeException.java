package com.demo.fileupload.web.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InValidFileSizeException extends BadRequestException {
    public InValidFileSizeException(String message) {
        super(message);
    }

    public InValidFileSizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
