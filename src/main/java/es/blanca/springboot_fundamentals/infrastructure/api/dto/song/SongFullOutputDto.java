package es.blanca.springboot_fundamentals.infrastructure.api.dto.song;


import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistMiniOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.genre.GenreOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist.PlaylistOutputDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongFullOutputDto {
	private Long id;
	private String name;
	private Integer duration;
	private GenreOutputDto genre;
	private ArtistMiniOutputDto artist;
	private List<PlaylistOutputDto> playlists;
}
