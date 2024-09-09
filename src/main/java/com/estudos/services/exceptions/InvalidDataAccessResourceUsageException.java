package com.estudos.services.exceptions;

public class InvalidDataAccessResourceUsageException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDataAccessResourceUsageException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidDataAccessResourceUsageException(String message) {
		super(message);

	}
}
