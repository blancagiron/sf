package es.blanca.springboot_fundamentals.infrastructure.api.controller;

import es.blanca.springboot_fundamentals.domain.model.Playlist;
import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.domain.port.in.PlaylistUseCase;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist.PlaylistFullOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist.PlaylistInputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist.PlaylistOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.mapper.PlaylistApiMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playlists")
@RequiredArgsConstructor
public class PlaylistController {
	private final PlaylistUseCase playlistUseCase;
	private final PlaylistApiMapper playlistApiMapper;

	@PostMapping
	public ResponseEntity<PlaylistOutputDto> createPlaylist(@Valid @RequestBody PlaylistInputDto inputDto) {
		Playlist playlist = playlistApiMapper.toDomain(inputDto);

		// Convertir songIds a List<Song> si existen
		if (inputDto.getSongIds() != null && !inputDto.getSongIds().isEmpty()) {
			List<Song> songs = inputDto.getSongIds().stream()
					.map(id -> Song.builder().id(id).build())
					.toList();
			playlist.setSongs(songs);
		}

		Playlist created = playlistUseCase.createPlaylist(playlist);
		return ResponseEntity.status(HttpStatus.CREATED).body(playlistApiMapper.toOutputDto(created));
	}

	@GetMapping
	public ResponseEntity<List<PlaylistOutputDto>> getAllPlaylists() {
		List<PlaylistOutputDto> playlists = playlistUseCase.getAllPlaylists().stream()
				.map(playlistApiMapper::toOutputDto)
				.toList();
		return ResponseEntity.ok(playlists);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlaylistOutputDto> getPlaylistById(@PathVariable Long id) {
		Playlist playlist = playlistUseCase.getPlaylistById(id);
		return ResponseEntity.ok(playlistApiMapper.toOutputDto(playlist));
	}

	@GetMapping("/{id}/full")
	public ResponseEntity<PlaylistFullOutputDto> getPlaylistByIdFull(@PathVariable Long id) {
		Playlist playlist = playlistUseCase.getPlaylistById(id);
		return ResponseEntity.ok(playlistApiMapper.toFullDto(playlist));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PlaylistOutputDto> updatePlaylist(@PathVariable Long id,
	                                                        @Valid @RequestBody PlaylistInputDto inputDto) {
		Playlist playlist = playlistApiMapper.toDomain(inputDto);
		Playlist updated = playlistUseCase.updatePlaylist(id, playlist);
		return ResponseEntity.ok(playlistApiMapper.toOutputDto(updated));
	}

	@PostMapping("/{id}/songs/{songId}")
	public ResponseEntity<Void> addSongToPlaylist(@PathVariable Long id, @PathVariable Long songId) {
		playlistUseCase.addSongToPlaylist(id, songId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}/songs/{songId}")
	public ResponseEntity<Void> removeSongFromPlaylist(@PathVariable Long id, @PathVariable Long songId) {
		playlistUseCase.removeSongFromPlaylist(id, songId);
		return ResponseEntity.noContent().build();
	}
}
