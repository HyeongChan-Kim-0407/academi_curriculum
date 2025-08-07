package khc.springboot.project4.tagoAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project4.tagoAPI.apiService.TagoRequestService;
import khc.springboot.project4.tagoAPI.dto.BusRouteDto;
import khc.springboot.project4.tagoAPI.service.TagoService;



@Controller
public class TagoController {
	
	@Autowired
	private TagoRequestService tagoRequestService;
	
	@Autowired
	private TagoService tagoService;
	
	@Autowired
	private HttpSession session;
	
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
	
	@GetMapping("/getRouteInfo")
	@ResponseBody
	public List<BusRouteDto> getRouteInfo(@RequestParam("cityCode") String cityCode, @RequestParam("routeId") String routeId) {
		System.out.println("버스 노선 정보 조회 요청");
		System.out.println("도시코드 : " + cityCode + ", 노선ID : " + routeId);
		
		List<BusRouteDto> data = tagoService.findBusRouteList(cityCode, routeId);
		
		return data;
	}
	
	@GetMapping("/logout")
	public String logout() {
		System.out.println("로그아웃 요청");
		// 세션 초기화
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	@PostMapping("/addFavorite")
	@ResponseBody
	public String addFavorite(@RequestParam("nodeid") String nodeid, @RequestParam("citycode") String citycode,
							@RequestParam("gpslati") String gpslati, @RequestParam("gpslong") String gpslong) {
		System.out.println("TagoController - 즐겨찾기 추가 요청");
		String loginUser = (String) session.getAttribute("loginId");
		
		return tagoService.addFavorite(nodeid, citycode, gpslati, gpslong, loginUser);
	}
	
	@PostMapping("/removeFavorite")
	@ResponseBody
	public String removeFavorite(@RequestParam("nodeid") String nodeid, @RequestParam("citycode") String citycode) {
		System.out.println("TagoController - 즐겨찾기 삭제 요청");
		String loginUser = (String) session.getAttribute("loginId");
		
		return tagoService.removeFavorite(nodeid, citycode, loginUser);
	}
	
}
