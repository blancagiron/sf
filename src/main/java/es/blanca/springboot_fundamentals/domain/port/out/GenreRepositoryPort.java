package es.blanca.springboot_fundamentals.domain.port.out;

import es.blanca.springboot_fundamentals.domain.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepositoryPort {
	Genre save(Genre genre);
	List<Genre> findAll();
	Optional<Genre> findById(Long id);
	void deleteById(Long id);
	boolean existsById(Long id);
}
