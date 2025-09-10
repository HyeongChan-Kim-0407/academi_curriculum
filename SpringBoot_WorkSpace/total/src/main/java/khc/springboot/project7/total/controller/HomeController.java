package khc.springboot.project7.total.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project7.total.dto.MemberDto;
import khc.springboot.project7.total.dto.WeatherDto;
import khc.springboot.project7.total.service.WeatherService;


@Controller
public class HomeController {
	
	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/")
	public String home(Model model) {
		if(httpSession.getAttribute("loginUser") != null) {
			MemberDto loginUser = (MemberDto)httpSession.getAttribute("loginUser");
			model.addAttribute("loginUser", loginUser);
		}else {
			model.addAttribute("loginUser", null);
		}
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
	
	@GetMapping("/getWeatherData")
	@ResponseBody
	public Map<String, List<WeatherDto>> getWeatherData(){
		
		// 학원(Default) 날씨 정보 조회
		Map<String, List<WeatherDto>> weatherData = weatherService.getDefaultWeather();
		
		MemberDto loginUser = (MemberDto)httpSession.getAttribute("loginUser");
		if(loginUser != null) {
			// 조회한 학원(Default) 날씨 + 해당 회원의 구독 지역 날씨 정보 조회
//			weatherData.put("subData", null);
			
		}
		sendWeather();
		
		return weatherData;
	}
	// 매 정각마다 실행
	@Scheduled(cron = "0 0/33 * * * * ")
	public void sendWeather(){
		messagingTemplate.convertAndSend("/ServerToClient/default", weatherService.getDefaultWeather());
		messagingTemplate.convertAndSend("/ServerToClient/서울", weatherService.apiTest("61", "125"));
		messagingTemplate.convertAndSend("/ServerToClient/인천", weatherService.apiTest("54", "125"));
		messagingTemplate.convertAndSend("/ServerToClient/부천", weatherService.apiTest("57", "125"));
	}
	
}
