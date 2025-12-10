package es.blanca.springboot_fundamentals.domain.exceptions;

public class GenreNotBelongArtistException extends RuntimeException {
	public GenreNotBelongArtistException(String message) {
		super(message);
	}
}
