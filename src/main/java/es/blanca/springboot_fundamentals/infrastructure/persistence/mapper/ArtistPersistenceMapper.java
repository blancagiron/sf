package es.blanca.springboot_fundamentals.infrastructure.persistence.mapper;

import es.blanca.springboot_fundamentals.domain.model.Artist;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.ArtistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {GenrePersistenceMapper.class})
public interface ArtistPersistenceMapper {
	@Mapping(target = "songs", ignore = true)
	Artist toDomain(ArtistEntity artistEntity);

	@Mapping(target = "songs", ignore = true)
	ArtistEntity toEntity(Artist artist);

}
