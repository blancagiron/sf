package es.blanca.springboot_fundamentals.application;

import com.sun.source.tree.AnnotatedTypeTree;
import es.blanca.springboot_fundamentals.application.utils.ApplicationConstants;
import es.blanca.springboot_fundamentals.domain.exceptions.ArtistAlreadyExistsException;
import es.blanca.springboot_fundamentals.domain.exceptions.ArtistInPlaylistException;
import es.blanca.springboot_fundamentals.domain.exceptions.ArtistNotFoundException;
import es.blanca.springboot_fundamentals.domain.exceptions.InvalidGenreException;
import es.blanca.springboot_fundamentals.domain.model.Artist;
import es.blanca.springboot_fundamentals.domain.model.Genre;
import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.domain.port.in.ArtistUseCase;
import es.blanca.springboot_fundamentals.domain.port.out.ArtistRepositoryPort;
import es.blanca.springboot_fundamentals.domain.port.out.GenreRepositoryPort;
import es.blanca.springboot_fundamentals.domain.port.out.SongRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ArtistUseCaseImpl implements ArtistUseCase {
	private final ArtistRepositoryPort artistRepositoryPort;
	private final GenreRepositoryPort genreRepositoryPort;
	private final SongRepositoryPort songRepositoryPort;

	@Override
	public Artist createArtist(Artist artist) {
		if(artistRepositoryPort.existsByName(artist.getName())){
			throw new ArtistAlreadyExistsException(String.format(ApplicationConstants.ARTIST_ALREADY_EXISTS, artist.getName()));
		}
		checkGenres(artist.getGenres());
		return artistRepositoryPort.save(artist);
	}

	@Override
	public Artist getArtistById(Long id) {
		return artistRepositoryPort.findById(id).
				orElseThrow(() -> new ArtistNotFoundException(String.format(ApplicationConstants.ARTIST_NOT_FOUND, id)));
	}

	@Override
	public List<Artist> getAllArtists() {
		return artistRepositoryPort.findAll();
	}

	@Override
	public Artist updateArtist(Long id, Artist artist) {

		Artist existingArtist = artistRepositoryPort.findById(id)
				.orElseThrow(() -> new ArtistNotFoundException(String.format(ApplicationConstants.ARTIST_NOT_FOUND, id)));


		if (artist.getName() != null
				&& !existingArtist.getName().equals(artist.getName())
				&& artistRepositoryPort.existsByName(artist.getName())) {
			throw new ArtistAlreadyExistsException(String.format(ApplicationConstants.ARTIST_ALREADY_EXISTS, artist.getName()));
		}


		if (artist.getGenres() != null && !artist.getGenres().isEmpty()) {
			checkGenres(artist.getGenres());
			existingArtist.setGenres(artist.getGenres());
		}

		if (artist.getName() != null) {
			existingArtist.setName(artist.getName());
		}
		if (artist.getStartDate() != null) {
			existingArtist.setStartDate(artist.getStartDate());
		}

		return artistRepositoryPort.save(existingArtist);
	}

	@Override
	public void deleteArtist(Long id) {
		artistRepositoryPort.findById(id)
				.orElseThrow(() -> new ArtistNotFoundException(String.format(ApplicationConstants.ARTIST_NOT_FOUND, id)));

		if(songRepositoryPort.playlistContainsSongsByArtist(id)){
			throw new ArtistInPlaylistException(ApplicationConstants.ARTIST_IN_PLAYLIST_DELETE_ERROR);
		}
		artistRepositoryPort.deleteById(id);
	}

	private void checkGenres(List<Genre> genres) {
		if(genres==null || genres.isEmpty()){
			return;
		}
		for(Genre genre : genres){
			if(!genreRepositoryPort.existsById(genre.getId())){
				throw new InvalidGenreException(ApplicationConstants.INVALID_GENRES);
			}
		}
	}
}
