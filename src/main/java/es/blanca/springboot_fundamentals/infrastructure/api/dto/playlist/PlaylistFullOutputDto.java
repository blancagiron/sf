package es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist;

import es.blanca.springboot_fundamentals.infrastructure.api.dto.song.SongMiniOutputDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistFullOutputDto {
	private Long id;
	private String name;
	private String description;
	private List<SongMiniOutputDto> songs;
}
