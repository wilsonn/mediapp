package com.whnm.mediappbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModeloNoFoundException extends RuntimeException{

	public ModeloNoFoundException(String mensaje) {
		super(mensaje);
	}
}
