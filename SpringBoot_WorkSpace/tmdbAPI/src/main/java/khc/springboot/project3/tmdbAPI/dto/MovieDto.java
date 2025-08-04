package khc.springboot.project3.tmdbAPI.dto;

import java.util.List;

import khc.springboot.project3.tmdbAPI.domain.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString	
public class MovieDto {
	
	private boolean adult;
	private String backdrop_path;
	private List<Integer> genre_ids;
	private int id;
	private String original_language;
	private String original_title;
	private String overview;
	private double popularity;
	private String poster_path;
	private String release_date;
	private String title;
	private boolean video;
	private double vote_average;
	private int vote_count;
	private String maximum;
	private String minimum;
	
	
	public MovieDto() {
		
	}
	
	public MovieDto(Movie movie) {
		this.id = movie.getMovieId();
		this.overview = movie.getOverview();
		this.poster_path = movie.getPoster_path();
		this.title = movie.getTitle();
		this.release_date = movie.getRelease_date();
	}
	
}
