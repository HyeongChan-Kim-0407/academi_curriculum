package khc.springboot.project7.total.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import khc.springboot.project7.total.dto.WeatherDto;
import khc.springboot.project7.total.service.WeatherService;

@Controller
public class HomeController {
	
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/apiTest")
	public String apiTest(Model model) {
		List<WeatherDto> weatherList = weatherService.apiTest();
		model.addAttribute("weatherList", weatherList);
		return "home";
	}
	
}
