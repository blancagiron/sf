package es.blanca.springboot_fundamentals.domain.exceptions;

public class ArtistAlreadyExistsException extends RuntimeException {
	public ArtistAlreadyExistsException(String message) {
		super(message);
	}
}
