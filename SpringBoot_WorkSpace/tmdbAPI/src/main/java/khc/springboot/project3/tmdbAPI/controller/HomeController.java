package khc.springboot.project3.tmdbAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import khc.springboot.project3.tmdbAPI.dto.MovieDto;
import khc.springboot.project3.tmdbAPI.service.MovieService;

@Controller
public class HomeController {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/")
	public String home(Model model) {
		System.out.println("/(get) 메인 페이지 이동요청");
		// upcoming 영화 목록 조회
		List<MovieDto> mvList = movieService.findUpcomingMovies();
		
		model.addAttribute("mvList", mvList);
		return "home";
	}
	
	@PostMapping("/searchMovie")
	public String searchMovie(@RequestParam("searchTitle") String title, Model model) {
		System.out.println("/searchMovie(post) 영화 검색 요청");
		System.out.println("검색어: " + title);
		
		List<MovieDto> mvList = movieService.findMovieByTitle(title);
		
		model.addAttribute("mvList", mvList);
		return "home";
	}
	
}
