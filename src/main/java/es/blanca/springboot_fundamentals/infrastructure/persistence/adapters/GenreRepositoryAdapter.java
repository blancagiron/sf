package es.blanca.springboot_fundamentals.infrastructure.persistence.adapters;

import es.blanca.springboot_fundamentals.domain.model.Genre;
import es.blanca.springboot_fundamentals.domain.port.out.GenreRepositoryPort;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.GenreEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.mapper.GenrePersistenceMapper;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.GenreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryAdapter implements GenreRepositoryPort {
	private final GenreJpaRepository genreJpaRepository;
	private final GenrePersistenceMapper genrePersistenceMapper;

	@Override
	public Genre save(Genre genre) {
		GenreEntity genreEntity = genrePersistenceMapper.toEntity(genre);
		GenreEntity genreEntitySaved = genreJpaRepository.save(genreEntity);
		return genrePersistenceMapper.toDomain(genreEntitySaved);
	}

	@Override
	public List<Genre> findAll() {
		return genreJpaRepository.findAll().stream()
				.map(genrePersistenceMapper::toDomain)
				.toList();
	}

	@Override
	public Optional<Genre> findById(Long id) {
		return genreJpaRepository.findById(id).map(genrePersistenceMapper::toDomain);
	}

	@Override
	public void deleteById(Long id) {
		genreJpaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return genreJpaRepository.existsById(id);
	}
}
