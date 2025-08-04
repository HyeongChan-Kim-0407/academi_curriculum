package khc.springboot.project3.tmdbAPI.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TmdbResponse {
	
	private int page;
	
	private List<MovieDto> results;
	
	private int total_pages;
	
	private int total_results;
	
	private Dates dates;
	
}
