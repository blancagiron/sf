package es.blanca.springboot_fundamentals.application.utils;

public class ApplicationConstants {
	private ApplicationConstants() {}
	// Generic
	public static final String FIELD_ALREADY_EXISTS = "Field already exists";
	public static final String ENTITY_NOT_FOUND = "Entity not found";

	// Artist
	public static final String ARTIST_NOT_FOUND = "Artist with id %d not found";
	public static final String ARTIST_ALREADY_EXISTS = "Artist with name %s already exists";
	public static final String ARTIST_IN_PLAYLIST_DELETE_ERROR = "Cannot delete artist because they have songs in a playlist";

	// Song
	public static final String SONG_NOT_FOUND = "Song with id %d not found";
	public static final String SONG_ALREADY_EXISTS = "Song already exists";
	public static final String SONG_GENRE_NOT_MATCH_ARTIST = "The song's genre does not belong to the selected artist";
	public static final String DURATION_MUST_BE_POSITIVE = "Duration must be a positive integer";

	// Playlist
	public static final String PLAYLIST_NOT_FOUND = "Playlist with id %d not found";
	public static final String SONG_NOT_IN_PLAYLIST = "Song with id %d is not in playlist with id %d";

	// Genre
	public static final String GENRE_NOT_FOUND = "Genre with id %d not found";
	public static final String INVALID_GENRES = "Cannot create/update because some genres are invalid";
	public static final String GENRE_NOT_BELONG_TO_ARTIST = "Genre does not belong to the artist";
}
