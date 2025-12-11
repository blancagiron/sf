package es.blanca.springboot_fundamentals.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Locale;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlists")
public class PlaylistEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name", nullable = false)
	private String name;

	@Column(name="description", nullable = false)
	private String description;

	@ManyToMany
	@JoinTable(
			name="playlists_songs",
			joinColumns = @JoinColumn(name="playlist_id"),
			inverseJoinColumns = @JoinColumn(name = "song_id")
	)
	private List<SongEntity> songs;
}
