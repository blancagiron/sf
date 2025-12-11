package es.blanca.springboot_fundamentals.infrastructure.persistence.repository;

import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistJpaRepository extends JpaRepository<PlaylistEntity, Long> {
}
