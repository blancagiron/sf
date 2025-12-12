package es.blanca.springboot_fundamentals.infrastructure.api.dto.song;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongInputDto {
	@NotNull(message = "Name cannot be null")
	@NotEmpty(message = "Name cannot be empty")
	private  String name;

	@NotNull(message = "Duration cannot be null")
	@Positive(message = "Duration must be a positive number")
	private Integer duration;

	@NotNull(message = "Genre ID cannot be null")
	private Long genreId;

	@NotNull(message = "Artist ID cannot be null")
	private Long artistId;




}
