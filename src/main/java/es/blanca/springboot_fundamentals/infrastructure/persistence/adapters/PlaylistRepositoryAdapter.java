package es.blanca.springboot_fundamentals.infrastructure.persistence.adapters;


import es.blanca.springboot_fundamentals.domain.model.Playlist;
import es.blanca.springboot_fundamentals.domain.port.out.PlaylistRepositoryPort;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.PlaylistEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.mapper.PlaylistPersistenceMapper;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.PlaylistJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlaylistRepositoryAdapter implements PlaylistRepositoryPort {
	private final PlaylistJpaRepository playlistJpaRepository;
	private final PlaylistPersistenceMapper playlistPersistenceMapper;


	@Override
	public Playlist save(Playlist playlist) {
		PlaylistEntity playlistEntity = playlistPersistenceMapper.toEntity(playlist);
		PlaylistEntity playlistEntitySaved = playlistJpaRepository.save(playlistEntity);
		return playlistPersistenceMapper.toDomain(playlistEntitySaved);
	}

	@Override
	public Optional<Playlist> findById(Long id) {
		return playlistJpaRepository.findById(id).map(playlistPersistenceMapper::toDomain);
	}

	@Override
	public List<Playlist> findAll() {
		return playlistJpaRepository.findAll().stream()
				.map(playlistPersistenceMapper::toDomain)
				.toList();
	}

	@Override
	public void deleteById(Long id) {
		playlistJpaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return playlistJpaRepository.existsById(id);
	}
}
