package khc.springboot.project4.tagoAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khc.springboot.project4.tagoAPI.apiService.TagoRequestService;

@Controller
public class TagoController {
	
	@Autowired
	private TagoRequestService tagoRequestService;
	
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/getStationList")
	@ResponseBody
	public String getStationList(@RequestParam("lat") String lat, @RequestParam("lng") String lng) {
		System.out.println("주변 정류소 조회 요청");
		System.out.println("위도 : " + lat + ", 경도 : " + lng);
		return tagoRequestService.getStationListByGps(lat, lng);
	}
	
	@GetMapping("/getBusList")
	@ResponseBody
	public String getBusList(@RequestParam("citycode") String citycode, @RequestParam("nodeid") String nodeid) {
		System.out.println("정류소 기반 버스 목록 조회 요청");
		System.out.println("도시코드 : " + citycode + ", 정류소ID : " + nodeid);
		return tagoRequestService.getBusListByStation(citycode, nodeid);
	}
	
}
