package com.ericksocop.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)

public class EmpleadosException extends RuntimeException {
    public EmpleadosException(String mensaje) {
        super(mensaje);
    
    }
}
