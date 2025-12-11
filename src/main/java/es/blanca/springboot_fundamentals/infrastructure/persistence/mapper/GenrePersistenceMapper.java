package es.blanca.springboot_fundamentals.infrastructure.persistence.mapper;

import es.blanca.springboot_fundamentals.domain.model.Genre;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.GenreEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel="spring")
public interface GenrePersistenceMapper {

	Genre toDomain(GenreEntity genreEntity);

	GenreEntity toEntity(Genre genre);

}
