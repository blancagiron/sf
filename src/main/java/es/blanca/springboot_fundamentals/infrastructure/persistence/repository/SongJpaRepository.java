package es.blanca.springboot_fundamentals.infrastructure.persistence.repository;

import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SongJpaRepository extends JpaRepository<SongEntity, Long> {
	boolean existsByNameAndArtistId(String name, Long artistId);


	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
			"FROM SongEntity s " +
			"WHERE s.artist.id = :artistId AND s.playlists IS NOT EMPTY")
	boolean playlistContainsSongsByArtist(@Param("artistId") Long artistId);
}
