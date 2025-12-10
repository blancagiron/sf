package es.blanca.springboot_fundamentals.domain.port.out;

import es.blanca.springboot_fundamentals.domain.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistRepositoryPort {
	Artist save(Artist artist);
	Optional<Artist> findById(Long id);
	List<Artist> findAll();
	void deleteById(Long id);
	boolean existsByName(String name);
}
