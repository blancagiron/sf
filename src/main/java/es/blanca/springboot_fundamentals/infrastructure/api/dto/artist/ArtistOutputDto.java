package es.blanca.springboot_fundamentals.infrastructure.api.dto.artist;


import es.blanca.springboot_fundamentals.infrastructure.api.dto.genre.GenreOutputDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistOutputDto {

	private Long id;
	private String name;
	private LocalDateTime startDate;
	private List<GenreOutputDto> genres;
}
