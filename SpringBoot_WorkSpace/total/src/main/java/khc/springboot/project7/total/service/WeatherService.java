package khc.springboot.project7.total.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project7.total.api.WeatherApi;
import khc.springboot.project7.total.dto.WeatherDto;

@Service
public class WeatherService {
	
	@Autowired
	private WeatherApi weatherApi;
	
	public Map<String, List<WeatherDto>> apiTest(String nx, String ny) {
		System.out.println("WeatherService.apiTest() 호출");
		
		
		Map<String, List<WeatherDto>> weatherMap = new LinkedHashMap<>();
		try {
			weatherMap = weatherApi.getFcstInfo(nx, ny);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return weatherMap;
		
	}
	
	
	
}
