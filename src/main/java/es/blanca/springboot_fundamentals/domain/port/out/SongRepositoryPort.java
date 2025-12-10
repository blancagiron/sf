package es.blanca.springboot_fundamentals.domain.port.out;

import es.blanca.springboot_fundamentals.domain.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongRepositoryPort {
	Song save(Song song);
	Optional<Song> findById(Long id);
	List<Song> findAll();
	void deleteById(Long id);
	boolean existsById(Long id);

	boolean playlistContainsSongsByArtist(Long artistId);
	boolean existsByNameAndArtistId(String name, Long artistId);
}
