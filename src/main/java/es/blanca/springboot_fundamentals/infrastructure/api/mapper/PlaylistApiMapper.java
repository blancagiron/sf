package es.blanca.springboot_fundamentals.infrastructure.api.mapper;

import es.blanca.springboot_fundamentals.domain.model.Playlist;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist.PlaylistFullOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist.PlaylistInputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist.PlaylistOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistApiMapper {
	PlaylistOutputDto toOutputDto(Playlist domain);

	PlaylistFullOutputDto toFullDto(Playlist domain);

	List<PlaylistOutputDto> toOutputDtoList(List<Playlist> domains);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "songs", ignore = true)
	Playlist toDomain(PlaylistInputDto inputDto);
}
