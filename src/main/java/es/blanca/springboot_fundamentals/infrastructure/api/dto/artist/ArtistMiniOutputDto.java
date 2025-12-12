package es.blanca.springboot_fundamentals.infrastructure.api.dto.artist;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistMiniOutputDto {
	private Long id;
	private String name;
}
