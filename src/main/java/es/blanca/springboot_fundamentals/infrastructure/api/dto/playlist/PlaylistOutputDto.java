package es.blanca.springboot_fundamentals.infrastructure.api.dto.playlist;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaylistOutputDto {
	private Long id;
	private String name;
	private String description;
}
