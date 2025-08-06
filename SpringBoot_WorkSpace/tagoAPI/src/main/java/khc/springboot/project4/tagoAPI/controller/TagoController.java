package khc.springboot.project4.tagoAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khc.springboot.project4.tagoAPI.apiService.TagoRequestService;
import khc.springboot.project4.tagoAPI.dto.BusRouteDto;
import khc.springboot.project4.tagoAPI.service.TagoService;



@Controller
public class TagoController {
	
	@Autowired
	private TagoRequestService tagoRequestService;
	
	@Autowired
	private TagoService tagoService;
	
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
	
}
