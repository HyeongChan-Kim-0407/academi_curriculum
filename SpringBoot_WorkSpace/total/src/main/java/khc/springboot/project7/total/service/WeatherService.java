package khc.springboot.project7.total.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import khc.springboot.project7.total.api.WeatherApi;
import khc.springboot.project7.total.dto.WeatherDto;

@Service
public class WeatherService {
	
	@Autowired
	private WeatherApi weatherApi;
	
	public Map<String, List<WeatherDto>> apiTest() {
		System.out.println("WeatherService.apiTest() 호출");
		LocalDate now = LocalDate.now();
		String baseDate = now.toString().replace("-", "");
		Map<String, List<WeatherDto>> weatherMap = weatherApi.apiTest(baseDate);
		return weatherMap;
		
	}
	
	
	
}
