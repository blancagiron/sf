package es.blanca.springboot_fundamentals.infrastructure.persistence.adapters;

import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.domain.port.out.SongRepositoryPort;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.SongEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.mapper.SongPersistenceMapper;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.SongJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SongRepositoryAdapter implements SongRepositoryPort {

	private final SongJpaRepository songJpaRepository;
	private final SongPersistenceMapper songPersistenceMapper;

	@Override
	public Song save(Song song) {
		SongEntity songEntity = songPersistenceMapper.toEntity(song);
		SongEntity songEntitySaved = songJpaRepository.save(songEntity);
		return songPersistenceMapper.toDomain(songEntitySaved);
	}

	@Override
	public Optional<Song> findById(Long id) {
		return songJpaRepository.findById(id).map(songPersistenceMapper::toDomain);
	}


	@Override
	public List<Song> findAll() {
		return songJpaRepository.findAll().stream()
				.map(songPersistenceMapper::toDomain)
				.toList();
	}

	@Override
	public void deleteById(Long id) {
		songJpaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return songJpaRepository.existsById(id);
	}

	@Override
	public boolean playlistContainsSongsByArtist(Long artistId) {
		return songJpaRepository.playlistContainsSongsByArtist(artistId);
	}

	@Override
	public boolean existsByNameAndArtistId(String name, Long artistId) {
		return songJpaRepository.existsByNameAndArtistId(name, artistId);
	}
}
