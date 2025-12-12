package es.blanca.springboot_fundamentals.infrastructure.api.mapper;

import es.blanca.springboot_fundamentals.domain.model.Artist;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistFullOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistInputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistMiniOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GenreApiMapper.class, SongApiMapper.class})
public interface ArtistApiMapper {
	ArtistOutputDto toOutputDto(Artist domain);

	ArtistFullOutputDto toFullDto(Artist domain);

	ArtistMiniOutputDto toMinimalDto(Artist domain);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "genres", ignore = true)
	@Mapping(target = "songs", ignore = true)
	Artist toDomain(ArtistInputDto inputDto);
}
