package khc.springboot.project4.tagoAPI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import khc.springboot.project4.tagoAPI.apiService.TagoRequestService;
import khc.springboot.project4.tagoAPI.domain.BusRoute;
import khc.springboot.project4.tagoAPI.dto.BusRouteDto;
import khc.springboot.project4.tagoAPI.repository.BusRouteRepository;

@Service
public class TagoService {

	@Autowired
	private TagoRequestService tagoRequestService;

	@Autowired
	private BusRouteRepository busRouteRepository;

	public List<BusRouteDto> findBusRouteList(String cityCode, String routeId) {

		List<BusRoute> busRouteList = busRouteRepository.findByCitycodeAndRouteid(cityCode, routeId);
		List<BusRouteDto> busRouteDtoList = new ArrayList<>();
		
		if (!busRouteList.isEmpty()) {
			System.out.println("repository에 존재함");
			for (BusRoute route : busRouteList) {
				BusRouteDto dto = new BusRouteDto(route);
				busRouteDtoList.add(dto);
			}
		} else {
			System.out.println("repository에 존재하지 않음");
			String response = tagoRequestService.getRouteInfoByRouteId(cityCode, routeId);
			Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Gson 객체 생성
			
			JsonArray itemArray = JsonParser.parseString(response).getAsJsonObject() 	// JSON 문자열을 JsonObject로 변환
											.get("response").getAsJsonObject() 			// "response" 키의 값을 가져옴
											.get("body").getAsJsonObject() 				// "body" 키의 값을 가져옴
											.get("items").getAsJsonObject()				// "items" 키의 값을 가져옴
											.get("item").getAsJsonArray(); 				// "item" 키의 값을 JsonArray로 가져옴
			// BusRoute Entity로 변환
			
			for(JsonElement item : itemArray) {
				BusRouteDto dto = gson.fromJson(item, BusRouteDto.class); //JSON 요소를 BusRouteDto 클래스로 변환
				dto.setCitycode(cityCode);
				busRouteDtoList.add(dto); // 변환된 BusRouteDto를 리스트에 추가
			}
			
			for(BusRouteDto dto : busRouteDtoList) {
				BusRoute busRoute = new BusRoute(dto); // BusRouteDto를 BusRoute로 변환
				busRouteList.add(busRoute);
			}
			busRouteRepository.saveAll(busRouteList); // DB에 저장
		}
		
		// TAGO - 버스 위치 정보 조회
		
		String locRes = tagoRequestService.getBusLocationByCityCodeAndRouteId(cityCode, routeId);
		
		JsonArray locArray = JsonParser.parseString(locRes).getAsJsonObject()
											.get("response").getAsJsonObject() 
											.get("body").getAsJsonObject() 
											.get("items").getAsJsonObject()
											.get("item").getAsJsonArray();
		for(JsonElement item : locArray) {
			for(BusRouteDto dto : busRouteDtoList) {
				if(item.getAsJsonObject().get("nodeid").getAsString().equals(dto.getNodeid())) {
					dto.setTrue(true);; // 해당 정류소에 버스가 정차해있는 경우
				}
			}
		}		
		
		return busRouteDtoList;
	}

}
