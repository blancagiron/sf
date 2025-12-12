package es.blanca.springboot_fundamentals.infrastructure.api.dto.song;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongMiniOutputDto {
	private Long id;
	private String name;
	private Integer duration;
}
