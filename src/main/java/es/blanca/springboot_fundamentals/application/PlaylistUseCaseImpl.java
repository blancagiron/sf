package es.blanca.springboot_fundamentals.application;

import es.blanca.springboot_fundamentals.application.utils.ApplicationConstants;
import es.blanca.springboot_fundamentals.domain.exceptions.PlaylistNotFoundException;
import es.blanca.springboot_fundamentals.domain.exceptions.SongNotFoundException;
import es.blanca.springboot_fundamentals.domain.model.Playlist;
import es.blanca.springboot_fundamentals.domain.model.Song;
import es.blanca.springboot_fundamentals.domain.port.in.PlaylistUseCase;
import es.blanca.springboot_fundamentals.domain.port.out.PlaylistRepositoryPort;
import es.blanca.springboot_fundamentals.domain.port.out.SongRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistUseCaseImpl implements PlaylistUseCase {

	private final PlaylistRepositoryPort playlistRepositoryPort;
	private final SongRepositoryPort songRepositoryPort;

	@Override
	public Playlist createPlaylist(Playlist playlist) {
		// filter only songs that are in the db
		if (playlist.getSongs() != null && !playlist.getSongs().isEmpty()) {
			List<Song> existingSongs =
					playlist.getSongs().stream()
							.map(Song::getId)
							.filter(Objects::nonNull)
							.map(songRepositoryPort::findById)
							.filter(Optional::isPresent)
							.map(Optional::get)
							.collect(Collectors.toList());

			playlist.setSongs(existingSongs);
		}

		return playlistRepositoryPort.save(playlist);
	}

	@Override
	public Playlist getPlaylistById(Long id) {
		return playlistRepositoryPort.findById(id)
				.orElseThrow(()-> new PlaylistNotFoundException(String.format(ApplicationConstants.PLAYLIST_NOT_FOUND, id)));
	}

	@Override
	public List<Playlist> getAllPlaylists() {
		return playlistRepositoryPort.findAll();
	}

	@Override
	public Playlist updatePlaylist(Long id, Playlist playlist) {
		Playlist existingPlaylist = playlistRepositoryPort.findById(id)
				.orElseThrow(()-> new PlaylistNotFoundException(String.format(ApplicationConstants.PLAYLIST_NOT_FOUND, id)));

		if(playlist.getName() != null){
			existingPlaylist.setName(playlist.getName());
		}
		if(playlist.getName() != null){
			existingPlaylist.setDescription(playlist.getDescription());
		}
		return playlistRepositoryPort.save(existingPlaylist);
	}

	@Override
	public void deletePlaylist(Long id) {
		if(!playlistRepositoryPort.existsById(id)){
			throw new PlaylistNotFoundException(String.format(ApplicationConstants.PLAYLIST_NOT_FOUND, id));
		}
		playlistRepositoryPort.deleteById(id);
	}

	@Override
	public void addSongToPlaylist(Long id, Long songId) {
		Playlist playlist = playlistRepositoryPort.findById(id)
				.orElseThrow(()-> new PlaylistNotFoundException(String.format(ApplicationConstants.PLAYLIST_NOT_FOUND, id)));

		Song songToAdd = songRepositoryPort.findById(songId)
				.orElseThrow(() -> new SongNotFoundException(String.format(ApplicationConstants.SONG_NOT_FOUND, songId)));

		if(playlist.getSongs() == null){
			playlist.setSongs(new ArrayList<>());
		}

		boolean isInPlaylist = false;
		for (Song song : playlist.getSongs()) {
			if (song.getId().equals(songToAdd.getId())) {
				isInPlaylist = true;
				break;
			}
		}

		if(!isInPlaylist){
			playlist.getSongs().add(songToAdd);
			playlistRepositoryPort.save(playlist);
		}
	}

	@Override
	public void removeSongFromPlaylist(Long id, Long songId) {
		Playlist playlist = playlistRepositoryPort.findById(id)
				.orElseThrow(() -> new PlaylistNotFoundException(
						String.format(ApplicationConstants.PLAYLIST_NOT_FOUND, id)));

		Song songToRemove = songRepositoryPort.findById(songId)
				.orElseThrow(() -> new SongNotFoundException(
						String.format(ApplicationConstants.SONG_NOT_FOUND, songId)));

		if (playlist.getSongs() != null) {
			Song songFound = null;
			for (Song song : playlist.getSongs()) {
				if (song.getId().equals(songToRemove.getId())) {
					songFound = song;
					break;
				}
			}

			if (songFound != null) {
				playlist.getSongs().remove(songFound);
				playlistRepositoryPort.save(playlist);
			}else{
				throw new SongNotFoundException(String.format(ApplicationConstants.SONG_NOT_FOUND, songId));
			}
		}
	}
}
