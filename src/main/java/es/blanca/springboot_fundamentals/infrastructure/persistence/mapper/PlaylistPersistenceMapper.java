package es.blanca.springboot_fundamentals.infrastructure.persistence.mapper;

import es.blanca.springboot_fundamentals.domain.model.Playlist;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.PlaylistEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SongPersistenceMapper.class})
public interface PlaylistPersistenceMapper {

	Playlist toDomain(PlaylistEntity playlist);
	PlaylistEntity toEntity(Playlist playlist);

}
