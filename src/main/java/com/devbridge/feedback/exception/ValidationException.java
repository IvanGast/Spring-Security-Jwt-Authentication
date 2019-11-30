package com.devbridge.feedback.exception;

import lombok.Getter;
import org.springframework.validation.Errors;

@Getter
public class ValidationException extends RuntimeException {

    private Errors errors;

    public ValidationException() {
    }

    public ValidationException(Errors errors) {
        this.errors = errors;
    }

}