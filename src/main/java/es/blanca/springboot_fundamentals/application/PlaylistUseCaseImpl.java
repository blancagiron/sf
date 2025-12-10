package es.blanca.springboot_fundamentals.application;

import es.blanca.springboot_fundamentals.domain.model.Playlist;
import es.blanca.springboot_fundamentals.domain.port.in.PlaylistUseCase;

import java.util.List;

public class PlaylistUseCaseImpl implements PlaylistUseCase {
	@Override
	public Playlist createPlaylist(Playlist playlist) {
		return null;
	}

	@Override
	public Playlist getPlaylistById(Long id) {
		return null;
	}

	@Override
	public List<Playlist> getAllPlaylists() {
		return List.of();
	}

	@Override
	public Playlist updatePlaylist(Long id, Playlist playlist) {
		return null;
	}

	@Override
	public void deletePlaylist(Long id) {

	}

	@Override
	public void addSongToPlaylist(Long id, Long songId) {

	}

	@Override
	public void removeSongFromPlaylist(Long id, Long songId) {

	}
}
