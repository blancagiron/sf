package es.blanca.springboot_fundamentals.domain.exceptions;

public class PlaylistNotFoundException extends RuntimeException {
	public PlaylistNotFoundException(String message) {
		super(message);
	}
}
