package es.blanca.springboot_fundamentals.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class SongEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name", nullable = false)
	private String name;

	@Column(name="duration", nullable = false)
	private Integer duration;

	@ManyToOne
	@JoinColumn(name="genre_id", nullable = false)
	private GenreEntity genre;

	@ManyToOne
	@JoinColumn(name="artist_id", nullable = false)
	private ArtistEntity artist;

	@ManyToMany(mappedBy = "songs")
	private List<PlaylistEntity> playlists;


}
