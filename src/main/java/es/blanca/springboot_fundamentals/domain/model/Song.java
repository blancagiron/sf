package es.blanca.springboot_fundamentals.domain.model;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Song {
	private Long id;
	private String name;
	private Integer duration;
	private Genre genre;
	private Artist artist;
	private List<Playlist> playlists;
}
