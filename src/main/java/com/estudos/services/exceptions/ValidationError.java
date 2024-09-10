package com.estudos.services.exceptions;

import java.util.ArrayList;
import java.util.List;

import com.estudos.controller.exceptions.StanderError;

public class ValidationError extends StanderError {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fiedlName, String message) {
		this.errors.add(new FieldMessage(fiedlName, message));
	}

}
