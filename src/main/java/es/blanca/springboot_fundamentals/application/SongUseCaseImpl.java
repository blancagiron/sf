package es.blanca.springboot_fundamentals.application;

import es.blanca.springboot_fundamentals.application.utils.ApplicationConstants;
import es.blanca.springboot_fundamentals.domain.exceptions.ArtistNotFoundException;
import es.blanca.springboot_fundamentals.domain.exceptions.GenreNotBelongArtistException;
import es.blanca.springboot_fundamentals.domain.exceptions.SongAlreadyExistsException;
import es.blanca.springboot_fundamentals.domain.exceptions.SongNotFoundException;
import es.blanca.springboot_fundamentals.domain.model.Artist;
import es.blanca.springboot_fundamentals.domain.model.Genre;
import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.domain.port.in.SongUseCase;
import es.blanca.springboot_fundamentals.domain.port.out.ArtistRepositoryPort;
import es.blanca.springboot_fundamentals.domain.port.out.SongRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongUseCaseImpl implements SongUseCase {

	private final SongRepositoryPort songRepositoryPort;
	private final ArtistRepositoryPort artistRepositoryPort;

	@Override
	public Song createSong(Song song) {
		Artist artist = artistRepositoryPort.findById(song.getArtist().getId())
				.orElseThrow(()-> new ArtistNotFoundException(String.format(ApplicationConstants.ARTIST_NOT_FOUND, song.getArtist().getId())));

		// check there isn´t already a song with the same name and artist
		if (songRepositoryPort.existsByNameAndArtistId(song.getName(), song.getArtist().getId())) {
			throw new SongAlreadyExistsException(String.format(ApplicationConstants.ARTIST_ALREADY_EXISTS, song.getName()));
		}

		// check that genre belongs to the artist
		checkGenreBelongsToArtist(song.getGenre(), artist);
		song.setArtist(artist);
		return songRepositoryPort.save(song);
	}

	@Override
	public Song getSongById(Long id) {
		return songRepositoryPort.findById(id).
				orElseThrow(() -> new SongNotFoundException(String.format(ApplicationConstants.SONG_NOT_FOUND, id)));
	}

	@Override
	public List<Song> getAllSongs() {
		return songRepositoryPort.findAll();
	}

	@Override
	public Song updateSong(Long id, Song song) {
		Song existingSong = songRepositoryPort.findById(id)
				.orElseThrow(() -> new SongNotFoundException(String.format(ApplicationConstants.SONG_NOT_FOUND, id)));

		Artist artist = existingSong.getArtist(); // original artist
		if(song.getArtist()!=null && song.getArtist().getId()!=null) { // check if there´s a different artist, if not, leave untouched the original artist
			artist = artistRepositoryPort.findById(song.getArtist().getId()) // check the id of the new artist exists
					.orElseThrow(() -> new ArtistNotFoundException(String.format(ApplicationConstants.ARTIST_NOT_FOUND, song.getArtist().getId())));

			existingSong.setArtist(artist);
		}
		// check the genre
		if (song.getGenre() != null) {
			checkGenreBelongsToArtist(song.getGenre(), artist);
			existingSong.setGenre(song.getGenre());
		}
		// check name
		if(song.getName()!=null) {
			existingSong.setName(song.getName());
		}
		// check duration
		if(song.getDuration()!=null) {
			existingSong.setDuration(song.getDuration());
		}
		return songRepositoryPort.save(existingSong);
	}

	@Override
	public void deleteSong(Long id) {
		if(!songRepositoryPort.existsById(id)) {
			throw new SongNotFoundException(String.format(ApplicationConstants.SONG_NOT_FOUND, id));
		}
		songRepositoryPort.deleteById(id);
	}

	@Override
	public Song updateSongDuration(Long id, Integer duration) {
		Song existingSong = songRepositoryPort.findById(id)
				.orElseThrow(() -> new SongNotFoundException(String.format(ApplicationConstants.SONG_NOT_FOUND, id)));
		existingSong.setDuration(duration);
		return songRepositoryPort.save(existingSong);
	}

	private void checkGenreBelongsToArtist(Genre songGenre, Artist artist) {
		boolean check = false;
		if(songGenre==null || artist.getGenres() == null || artist.getGenres().isEmpty()){
			return;
		}
		for(Genre g : artist.getGenres()){
			if(g.getId().equals(songGenre.getId())){
				check = true;
				break;
			}
		}
		if(!check){
			throw new GenreNotBelongArtistException(ApplicationConstants.GENRE_NOT_BELONG_TO_ARTIST);
		}
	}
}
