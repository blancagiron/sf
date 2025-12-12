package es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistInputDto {
	@NotBlank(message = "Name cannot be empty")
	private String name;

	@NotBlank(message = "Description cannot be empty")
	@Pattern(
			regexp = "^(\\S+\\s+){4,14}\\S+$",
			message = "Description must be between 5 and 15 words"
	)
	private String description;

	private List<Long> songIds;
}
