package es.blanca.springboot_fundamentals.infrastructure.persistence.adapters;


import es.blanca.springboot_fundamentals.domain.model.Artist;
import es.blanca.springboot_fundamentals.domain.port.out.ArtistRepositoryPort;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.ArtistEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.mapper.ArtistPersistenceMapper;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.ArtistJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtistRepositoryAdapter implements ArtistRepositoryPort {

	private final ArtistJpaRepository artistJpaRepository;
	private final ArtistPersistenceMapper artistPersistenceMapper;

	@Override
	public Artist save(Artist artist) {
		ArtistEntity artistEntity = artistPersistenceMapper.toEntity(artist);
		ArtistEntity artistEntitySaved = artistJpaRepository.save(artistEntity);
		return  artistPersistenceMapper.toDomain(artistEntitySaved);
	}

	@Override
	public Optional<Artist> findById(Long id) {
		return artistJpaRepository.findById(id).map(artistPersistenceMapper::toDomain);
	}

	@Override
	public List<Artist> findAll() {
		return artistJpaRepository.findAll().stream()
				.map(artistPersistenceMapper::toDomain)
				.toList();
	}

	@Override
	public void deleteById(Long id) {
		artistJpaRepository.deleteById(id);
	}

	@Override
	public boolean existsByName(String name) {
		return artistJpaRepository.existsByName(name);
	}
}
