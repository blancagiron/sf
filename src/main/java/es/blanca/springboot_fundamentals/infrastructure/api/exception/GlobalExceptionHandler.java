package es.blanca.springboot_fundamentals.infrastructure.api.exception;

import es.blanca.springboot_fundamentals.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {
	private CustomError buildError(String message, HttpStatus status) {
		return CustomError.builder()
				.timestamp(LocalDate.now())
				.httpCode(status.value())
				.message(message)
				.build();
	}

	// 404 - Not Found
	@ExceptionHandler(ArtistNotFoundException.class)
	public ResponseEntity<CustomError> handleArtistNotFoundException(ArtistNotFoundException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(SongNotFoundException.class)
	public ResponseEntity<CustomError> handleSongNotFoundException(SongNotFoundException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PlaylistNotFoundException.class)
	public ResponseEntity<CustomError> handlePlaylistNotFoundException(PlaylistNotFoundException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(GenreNotFoundException.class)
	public ResponseEntity<CustomError> handleGenreNotFoundException(GenreNotFoundException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// 400 - Bad Request
	@ExceptionHandler(ArtistAlreadyExistsException.class)
	public ResponseEntity<CustomError> handleArtistAlreadyExistsException(ArtistAlreadyExistsException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SongAlreadyExistsException.class)
	public ResponseEntity<CustomError> handleSongAlreadyExistsException(SongAlreadyExistsException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ArtistInPlaylistException.class)
	public ResponseEntity<CustomError> handleArtistInPlaylistException(ArtistInPlaylistException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(GenreNotBelongArtistException.class)
	public ResponseEntity<CustomError> handleGenreNotBelongArtistException(GenreNotBelongArtistException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidGenreException.class)
	public ResponseEntity<CustomError> handleInvalidGenreException(InvalidGenreException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateFieldException.class)
	public ResponseEntity<CustomError> handleDuplicateFieldException(DuplicateFieldException ex) {
		CustomError error = buildError(ex.getMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	// Validaciones de DTOs (@Valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> handleValidationExceptions(MethodArgumentNotValidException ex) {
		CustomError error = buildError(ex.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
