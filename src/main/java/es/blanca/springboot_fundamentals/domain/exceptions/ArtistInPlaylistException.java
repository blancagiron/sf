package es.blanca.springboot_fundamentals.domain.exceptions;

public class ArtistInPlaylistException extends RuntimeException {
	public ArtistInPlaylistException(String message) {
		super(message);
	}
}
