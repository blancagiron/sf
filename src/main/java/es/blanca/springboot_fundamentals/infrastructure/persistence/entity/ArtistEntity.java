package es.blanca.springboot_fundamentals.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artist")
public class ArtistEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name ="name", unique = true, nullable = false)
	private String name;

	@Column(name="start_date", nullable = false)
	private LocalDateTime startDate;

	// an artist can have many genres, a genre can be in many artists
	@ManyToMany
	@JoinTable(
			name = "artist_genres", // middle table
			joinColumns = @JoinColumn(name="artist_id"),
			inverseJoinColumns = @JoinColumn(name="genre_id")
	)
	private List<GenreEntity> genres;

	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SongEntity> songs;


}
