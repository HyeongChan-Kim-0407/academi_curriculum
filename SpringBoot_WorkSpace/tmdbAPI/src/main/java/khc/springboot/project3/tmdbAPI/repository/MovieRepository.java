package khc.springboot.project3.tmdbAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project3.tmdbAPI.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	List<Movie> findByTitleContaining(String title);
	
	
	
}
