package es.blanca.springboot_fundamentals.infrastructure.api.dto.song;


import es.blanca.springboot_fundamentals.infrastructure.api.dto.artist.ArtistMiniOutputDto;
import es.blanca.springboot_fundamentals.infrastructure.api.dto.genre.GenreOutputDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongOutputDto {
	private Long id;
	private String name;
	private Integer duration;
	private GenreOutputDto genre;
	private ArtistMiniOutputDto artist;
}
