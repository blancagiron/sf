package es.blanca.springboot_fundamentals.domain.exceptions;

public class InvalidGenreException extends RuntimeException {
	public InvalidGenreException(String message) {
		super(message);
	}
}
