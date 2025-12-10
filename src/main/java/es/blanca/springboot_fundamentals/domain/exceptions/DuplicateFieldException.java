package es.blanca.springboot_fundamentals.domain.exceptions;

public class DuplicateFieldException extends RuntimeException {
	public DuplicateFieldException(String message) {
		super(message);
	}
}
