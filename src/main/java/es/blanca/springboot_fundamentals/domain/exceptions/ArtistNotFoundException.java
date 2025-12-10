package es.blanca.springboot_fundamentals.domain.exceptions;

public class ArtistNotFoundException extends RuntimeException {
	public ArtistNotFoundException(String message) {
		super(message);
	}
}
