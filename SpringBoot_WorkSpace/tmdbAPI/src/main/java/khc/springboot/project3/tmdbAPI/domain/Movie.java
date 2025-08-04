package khc.springboot.project3.tmdbAPI.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import khc.springboot.project3.tmdbAPI.dto.MovieDto;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Movie {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private int movieId; // movieDto의 id(TMDB의 Movie id)
	
	@Column(length = 3000)
	private String overview; //
	
	private String poster_path;
	
	private String title;
	
	private String release_date;
	
	public Movie() {
		
	}
	
	public Movie(MovieDto movieDto) {
		this.movieId = movieDto.getId();
		this.overview = movieDto.getOverview();
		this.poster_path = movieDto.getPoster_path();
		this.title = movieDto.getTitle();
		this.release_date = movieDto.getRelease_date();
	}
	
}
