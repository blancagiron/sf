package es.blanca.springboot_fundamentals.infrastructure.api.controller;

import es.blanca.springboot_fundamentals.domain.model.Artist;
import es.blanca.springboot_fundamentals.domain.model.Genre;
import es.blanca.springboot_fundamentals.domain.port.in.ArtistUseCase;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistFullOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistInputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.mapper.ArtistApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/artists")
@RequiredArgsConstructor
public class ArtistController {
	private final ArtistUseCase artistUseCase;
	private final ArtistApiMapper artistApiMapper;

	@PostMapping
	public ResponseEntity<ArtistOutputDto> createArtist(@Valid @RequestBody ArtistInputDto inputDto) {
		Artist artist = artistApiMapper.toDomain(inputDto);

		List<Genre> genres = inputDto.getGenreIds().stream()
				.map(id -> Genre.builder().id(id).build())
				.toList();
		artist.setGenres(genres);

		Artist created = artistUseCase.createArtist(artist);
		return new ResponseEntity<>(artistApiMapper.toOutputDto(created), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<ArtistOutputDto>> getAllArtists() {
		List<ArtistOutputDto> artists = artistUseCase.getAllArtists().stream()
				.map(artistApiMapper::toOutputDto)
				.toList();
		return ResponseEntity.ok(artists);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ArtistOutputDto> getArtistById(@PathVariable Long id) {
		Artist artist = artistUseCase.getArtistById(id);
		return ResponseEntity.ok(artistApiMapper.toOutputDto(artist));
	}

	@GetMapping("/{id}/full")
	public ResponseEntity<ArtistFullOutputDto> getArtistByIdFull(@PathVariable Long id) {
		Artist artist = artistUseCase.getArtistById(id);
		return ResponseEntity.ok(artistApiMapper.toFullDto(artist));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ArtistOutputDto> updateArtist(@PathVariable Long id,
	                                                    @Valid @RequestBody ArtistInputDto inputDto) {
		Artist artist = artistApiMapper.toDomain(inputDto);

		List<Genre> genres = inputDto.getGenreIds().stream()
				.map(genreId -> Genre.builder().id(genreId).build())
				.toList();
		artist.setGenres(genres);

		Artist updated = artistUseCase.updateArtist(id, artist);
		return ResponseEntity.ok(artistApiMapper.toOutputDto(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
		artistUseCase.deleteArtist(id);
		return ResponseEntity.noContent().build();
	}
}
