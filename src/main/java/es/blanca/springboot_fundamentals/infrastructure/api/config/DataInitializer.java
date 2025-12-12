package es.blanca.springboot_fundamentals.infrastructure.api.config;

import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.ArtistEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.GenreEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.PlaylistEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.entity.SongEntity;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.ArtistJpaRepository;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.GenreJpaRepository;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.PlaylistJpaRepository;
import es.blanca.springboot_fundamentals.infrastructure.persistence.repository.SongJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
	private final GenreJpaRepository genreRepository;
	private final ArtistJpaRepository artistRepository;
	private final SongJpaRepository songRepository;
	private final PlaylistJpaRepository playlistRepository;

	@Override
	public void run(String... args) {
		// Solo inicializar si la BD está vacía para no duplicar datos al reiniciar
		if (genreRepository.count() > 0) {
			return;
		}

		// 1. Géneros
		GenreEntity rock = genreRepository.save(GenreEntity.builder().name("Rock").build());
		GenreEntity pop = genreRepository.save(GenreEntity.builder().name("Pop").build());
		GenreEntity jazz = genreRepository.save(GenreEntity.builder().name("Jazz").build());
		GenreEntity electronic = genreRepository.save(GenreEntity.builder().name("Electronic").build());

		// 2. Artistas
		ArtistEntity metallica = artistRepository.save(ArtistEntity.builder()
				.name("Metallica")
				.startDate(LocalDateTime.of(1981, 10, 28, 0, 0))
				.genres(List.of(rock)) // Relación ManyToMany
				.build());

		ArtistEntity madonna = artistRepository.save(ArtistEntity.builder()
				.name("Madonna")
				.startDate(LocalDateTime.of(1982, 1, 1, 0, 0))
				.genres(List.of(pop))
				.build());

		// 3. Canciones
		SongEntity enterSandman = songRepository.save(SongEntity.builder()
				.name("Enter Sandman")
				.duration(331)
				.genre(rock)
				.artist(metallica)
				.build());

		SongEntity nothingElseMatters = songRepository.save(SongEntity.builder()
				.name("Nothing Else Matters")
				.duration(388)
				.genre(rock)
				.artist(metallica)
				.build());

		SongEntity likeAPrayer = songRepository.save(SongEntity.builder()
				.name("Like a Prayer")
				.duration(340)
				.genre(pop)
				.artist(madonna)
				.build());

		// 4. Playlists
		playlistRepository.save(PlaylistEntity.builder()
				.name("Rock Classics")
				.description("The best rock songs from the eighties and nineties") // Entre 5 y 15 palabras
				.songs(List.of(enterSandman, nothingElseMatters))
				.build());

		playlistRepository.save(PlaylistEntity.builder()
				.name("Pop Hits")
				.description("Greatest pop hits of all time for everyone")
				.songs(List.of(likeAPrayer))
				.build());

		System.out.println("--- DATOS DE PRUEBA CARGADOS CORRECTAMENTE ---");
	}
}
