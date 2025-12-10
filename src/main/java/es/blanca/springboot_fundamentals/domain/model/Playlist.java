package es.blanca.springboot_fundamentals.domain.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
	private Long id;
	private String name;
	private String description;
	List<Song> songs;
}
