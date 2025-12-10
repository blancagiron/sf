package es.blanca.springboot_fundamentals.domain.port.in;

import es.blanca.springboot_fundamentals.domain.model.Artist;
import es.blanca.springboot_fundamentals.domain.model.Song;

import java.util.List;

public interface SongUseCase {
	Song createSong(Song song);
	Song getSongById(Long id);
	List<Song> getAllSongs();
	Song updateSong(Long id, Song song);
	void deleteSong(Long id);
	Song updateSongDuration(Long id, Integer duration);
}
