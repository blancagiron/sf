package es.blanca.springboot_fundamentals.infrastructure.persistence.repository;

import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistJpaRepository extends JpaRepository<ArtistEntity, Long> {
	boolean existsByName(String name);
}
