package es.blanca.springboot_fundamentals.domain.port.in;

import es.blanca.springboot_fundamentals.domain.model.Playlist;


import java.util.List;

public interface PlaylistUseCase {
	Playlist createPlaylist(Playlist playlist);
	Playlist getPlaylistById(Long id);
	List<Playlist> getAllPlaylists();
	Playlist updatePlaylist(Long id, Playlist playlist);
	void deletePlaylist(Long id);
	void addSongToPlaylist(Long id, Long songId);
	void removeSongFromPlaylist(Long id, Long songId);


}
