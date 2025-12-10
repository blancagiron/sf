package es.blanca.springboot_fundamentals.domain.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
	private Long id;
	private String name;
	private LocalDateTime startDate;
	private List<Genre> genres;
	private List<Song> songs;
}
