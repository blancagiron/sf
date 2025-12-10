package es.blanca.springboot_fundamentals.domain.exceptions;

public class GenreNotFoundException extends RuntimeException {
	public GenreNotFoundException(String message) {
		super(message);
	}
}
