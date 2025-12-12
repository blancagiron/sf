package es.blanca.springboot_fundamentals.infrastructure.api.dto.genre;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreOutputDto {
	private Long id;
	private String name;
}
