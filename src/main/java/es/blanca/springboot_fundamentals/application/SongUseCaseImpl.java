package es.blanca.springboot_fundamentals.application;

import es.blanca.springboot_fundamentals.application.utils.ApplicationConstants;
import es.blanca.springboot_fundamentals.domain.exceptions.SongNotFoundException;
import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.domain.port.in.SongUseCase;
import es.blanca.springboot_fundamentals.domain.port.out.SongRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongUseCaseImpl implements SongUseCase {

	private SongRepositoryPort songRepositoryPort;

	@Override
	public Song createSong(Song song) {
		if(songRepositoryPort.ex) {}
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
		return null;
	}

	@Override
	public void deleteSong(Long id) {

	}

	@Override
	public Song updateSongDuration(Long id, Integer duration) {
		return null;
	}
}
