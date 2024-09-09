package com.estudos.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estudos.services.exceptions.InvalidDataAccessResourceUsageException;
import com.estudos.services.exceptions.ObjectNotFoundExceptions;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundExceptions.class)
	public ResponseEntity<StanderError> objNotFoundException(ObjectNotFoundExceptions ex, HttpServletRequest request) {

		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Object not found", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}
	
	
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public ResponseEntity<StanderError> invalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex, HttpServletRequest request) {

		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Violação de dados", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

}
