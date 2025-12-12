package es.blanca.springboot_fundamentals.infrastructure.api.mapper;

import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongFullOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongInputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongMiniOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreApiMapper.class, ArtistApiMapper.class, PlaylistApiMapper.class})
public interface SongApiMapper {
	SongOutputDto toOutputDto(Song domain);

	SongFullOutputDto toFullDto(Song domain);

	SongMiniOutputDto toMinimalDto(Song domain);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "genre.id", source = "genreId")
	@Mapping(target = "artist.id", source = "artistId")
	@Mapping(target = "playlists", ignore = true)
	Song toDomain(SongInputDto inputDto);
}
