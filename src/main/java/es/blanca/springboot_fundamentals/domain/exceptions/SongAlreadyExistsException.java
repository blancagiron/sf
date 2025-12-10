package es.blanca.springboot_fundamentals.domain.exceptions;

public class SongAlreadyExistsException extends RuntimeException {
	public SongAlreadyExistsException(String message) {
		super(message);
	}
}
