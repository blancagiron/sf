package es.blanca.springboot_fundamentals.domain.port.out;

import es.blanca.springboot_fundamentals.domain.model.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepositoryPort {
	Playlist save(Playlist playlist);
	Optional<Playlist> findById(Long id);
	List<Playlist> findAll();
	void deleteById(Long id);
	boolean existsById(Long id);
}
