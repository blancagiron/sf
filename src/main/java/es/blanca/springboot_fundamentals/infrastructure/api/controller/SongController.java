package es.blanca.springboot_fundamentals.infrastructure.api.controller;

import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.domain.port.in.SongUseCase;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongFullOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongInputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.mapper.SongApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {
	private final SongUseCase songUseCase;
	private final SongApiMapper songApiMapper;

	@PostMapping
	public ResponseEntity<SongOutputDto> createSong(@Valid @RequestBody SongInputDto inputDto) {
		Song song = songApiMapper.toDomain(inputDto);
		Song created = songUseCase.createSong(song);
		return ResponseEntity.status(HttpStatus.CREATED).body(songApiMapper.toOutputDto(created));
	}

	@GetMapping
	public ResponseEntity<List<SongOutputDto>> getAllSongs() {
		List<SongOutputDto> songs = songUseCase.getAllSongs().stream()
				.map(songApiMapper::toOutputDto)
				.toList();
		return ResponseEntity.ok(songs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SongOutputDto> getSongById(@PathVariable Long id) {
		Song song = songUseCase.getSongById(id);
		return ResponseEntity.ok(songApiMapper.toOutputDto(song));
	}

	@GetMapping("/{id}/full")
	public ResponseEntity<SongFullOutputDto> getSongByIdFull(@PathVariable Long id) {
		Song song = songUseCase.getSongById(id);
		return ResponseEntity.ok(songApiMapper.toFullDto(song));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SongOutputDto> updateSong(@PathVariable Long id,
	                                                @Valid @RequestBody SongInputDto inputDto) {
		Song song = songApiMapper.toDomain(inputDto);
		Song updated = songUseCase.updateSong(id, song);
		return ResponseEntity.ok(songApiMapper.toOutputDto(updated));
	}

	@PatchMapping("/{id}/{duration}")
	public ResponseEntity<SongOutputDto> updateSongDuration(@PathVariable Long id,
	                                                        @PathVariable Integer duration) {
		Song updated = songUseCase.updateSongDuration(id, duration);
		return ResponseEntity.ok(songApiMapper.toOutputDto(updated));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
		songUseCase.deleteSong(id);
		return ResponseEntity.noContent().build();
	}
}
