package es.blanca.springboot_fundamentals.infrastructure.persistence.mapper;

import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GenrePersistenceMapper.class, ArtistPersistenceMapper.class})
public interface SongPersistenceMapper {
	@Mapping(target = "playlists", ignore = true)
	Song toDomain(SongEntity entity);

	@Mapping(target = "playlists", ignore = true)
	SongEntity toEntity(Song song);

}
