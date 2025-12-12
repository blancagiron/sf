package es.blanca.springboot_fundamentals.infrastructure.api.dto.artist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistInputDto {

	@NotBlank(message = "Name cannot be empty or blank")
	private String name;

	@NotNull(message = "Start date cannot be null")
	@PastOrPresent(message = " Start date cannot be in the future")
	private LocalDateTime startDate;

	@NotNull(message = "Genre list cannot be null")
	@NotEmpty(message = "Artist must have at least one genre ")
	private List<Long> genreIds;

}

