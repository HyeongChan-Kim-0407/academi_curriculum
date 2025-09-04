package khc.springboot.project7.total.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public Map<String, List<WeatherDto>> apiTest(@RequestParam("nx") String nx, @RequestParam("ny") String ny) {
		Map<String, List<WeatherDto>> weatherMap = weatherService.apiTest(nx, ny);
		return weatherMap;
	}
	
	@GetMapping("/firstWeather")
	@ResponseBody
	public Map<String, List<WeatherDto>> firstWeather(@RequestParam("nx") String nx, @RequestParam("ny") String ny) {
		Map<String, List<WeatherDto>> weatherMap = weatherService.apiTest(nx, ny);
		return weatherMap;
	}
	
	@GetMapping("/secondWeather")
	@ResponseBody
	public Map<String, List<WeatherDto>> secondWeather(@RequestParam("nx") String nx, @RequestParam("ny") String ny) {
		Map<String, List<WeatherDto>> weatherMap = weatherService.apiTest(nx, ny);
		return weatherMap;
	}
	
}
