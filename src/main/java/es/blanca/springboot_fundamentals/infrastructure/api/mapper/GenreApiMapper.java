package es.blanca.springboot_fundamentals.infrastructure.api.mapper;

import es.blanca.springboot_fundamentals.domain.model.Genre;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.genre.GenreOutputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenreApiMapper {

	GenreOutputDto toOutputDto(Genre genre);
	Genre toDomain(GenreOutputDto genreOutputDto);
}
