package com.gabrielrochads.cliente_api.exception;

public class DomainConflictException extends RuntimeException {
    public DomainConflictException(String msg) {
        super(msg);
    }
}
