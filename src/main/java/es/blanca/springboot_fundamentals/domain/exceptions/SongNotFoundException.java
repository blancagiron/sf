package es.blanca.springboot_fundamentals.domain.exceptions;

public class SongNotFoundException extends RuntimeException {
	public SongNotFoundException(String message) {
		super(message);
	}
}
