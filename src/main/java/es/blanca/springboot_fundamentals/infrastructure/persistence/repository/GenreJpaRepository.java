package es.blanca.springboot_fundamentals.infrastructure.persistence.repository;

import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
}
