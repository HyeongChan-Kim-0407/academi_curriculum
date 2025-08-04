package khc.springboot.project3.tmdbAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import khc.springboot.project3.tmdbAPI.apiService.TmdbApiService;
import khc.springboot.project3.tmdbAPI.domain.Movie;
import khc.springboot.project3.tmdbAPI.dto.Dates;
import khc.springboot.project3.tmdbAPI.dto.MovieDto;
import khc.springboot.project3.tmdbAPI.dto.TmdbResponse;
import khc.springboot.project3.tmdbAPI.repository.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private TmdbApiService tmdbApi;
	
	@Autowired
	private MovieRepository movieRepository;
	
	public List<MovieDto> findMovieByTitle(String title) {
		System.out.println("MovieService-findMovieByTitle() 영화 검색 서비스 호출");
//		1. title을 이용하여 MovieRepository에서 조회
		List<Movie> mvList = movieRepository.findByTitleContaining(title); // Containing : title이 포함된 항목 조회 (Like)
//		2. 조회 결과가 있으면 controller에 return
		if(!mvList.isEmpty()) {
			List<MovieDto> mvDtoList = new ArrayList<>();
			for(Movie mv : mvList) {
				MovieDto movieDto = new MovieDto(mv);
				mvDtoList.add(movieDto);
			}
			return mvDtoList;
		}
//		2-1. 조회결과가 없으면 TMDB API에 영화 SEARCH 요청
		String response = tmdbApi.searchByMovieTitle(title);
//		System.out.println(response); json 형식의 String
//		3. 응답 데이터 변환 > TmdbResponse 타입으로 변환
		// Gson(Google json)  라이브러리 사용
		Gson gson = new Gson();
		TmdbResponse tmdbRes = gson.fromJson(response, TmdbResponse.class);
		List<MovieDto> movieDtoList = tmdbRes.getResults();
//		System.out.println(movieDtoList);
//		4. MovieRepository에 save
		List<Movie> movieList = new ArrayList<>();
		for(MovieDto mvDto : movieDtoList) {
			Movie movie = new Movie(mvDto);
			movieList.add(movie);
		}
		movieRepository.saveAll(movieList);
//		5. 결과를 controller에 return
		return movieDtoList;
	}

	public List<MovieDto> findUpcomingMovies() {
		String response = tmdbApi.findUpcomingMovies();
		Gson gson = new Gson();
		TmdbResponse tmdbRes = gson.fromJson(response, TmdbResponse.class);
		List<MovieDto> movieDtoList = tmdbRes.getResults();
		Dates dates = tmdbRes.getDates();
		for(MovieDto mvDto : movieDtoList) {
			mvDto.setMaximum(dates.getMaximum());
			mvDto.setMinimum(dates.getMinimum());
		}
		
		return movieDtoList;
	}
	
	
	
}
