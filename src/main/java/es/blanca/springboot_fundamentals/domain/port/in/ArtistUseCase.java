package es.blanca.springboot_fundamentals.domain.port.in;

import es.blanca.springboot_fundamentals.domain.model.Artist;

import java.util.List;

public interface ArtistUseCase {
	Artist createArtist(Artist artist);
	Artist getArtistById(Long id);
	List<Artist> getAllArtists();
	Artist updateArtist(Long id, Artist artist);
	void deleteArtist(Long id);

}
