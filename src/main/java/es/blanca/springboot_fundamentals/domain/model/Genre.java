package es.blanca.springboot_fundamentals.domain.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Genre {
	private Long id;
	private String name;
}
