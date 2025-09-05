package khc.springboot.project7.total.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import khc.springboot.project7.total.dto.WeatherDto;

@Component
public class WeatherApi {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	private void sample() {
		messagingTemplate.convertAndSend("/주소", "메시지");
	}
	
	// 학원 날씨 조회 기능 메소드
	public Map<String, List<WeatherDto>> findDefaultWeather() throws Exception {
		// 단기 예보 조회 요청
		Map<String, List<WeatherDto>> weatherMap = getFcstInfo("55", "127");
		// key : 202509051800 value : List<WeatherDto>
		// 초단기 실황 조회 요청
		List<WeatherDto> ncstData = getNcstInfo("55", "127");
		
		weatherMap.put("ncstData", ncstData);
		// key : ncstData value : List<WeatherDto> ncstData
		
		return weatherMap;
	}
	
	
	public Map<String, List<WeatherDto>> apiTest(String requestType, String nx, String ny) throws Exception {
		System.out.println("WeatherApi.apiTest() 호출");
		String requestUrl = "/getUltraSrtNcst";
		List<WeatherDto> weatherList = new ArrayList<>();
		if(requestType.equals("Fcst")) {
			requestUrl = "/getUltraSrtFcst";
		}
		LocalDateTime now = LocalDateTime.now();
		String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String baseTime = now.format(DateTimeFormatter.ofPattern("HHmm"));
			// 초단기 실황 호출
			// /getUltraSrtNcst
			String ultraSrtNcst = SSWeather("/getUltraSrtNcst", baseDate, nx, ny);
			System.out.println("초단기 실황");
//			System.out.println(ultraSrtNcst);
//			jsonToDto(ultraSrtNcst, "초단기 실황");
			
			// 초단기 예보 호출
			// /getUltraSrtFcst
			String ultraSrtFcst = SSWeather("/getUltraSrtFcst", baseDate, nx, ny);
			System.out.println("초단기 예보");
//			System.out.println(ultraSrtFcst);
			
			// JSON > DtoList
			weatherList = jsonToDto(ultraSrtFcst, "초단기 예보");
			
			// 날짜 + 시간 별로 정렬
			Map<String, List<WeatherDto>> weatherMap = collectByDate(weatherList);
			
//			System.out.println(new Gson().toJson(weatherMap)); // 데이터 변환 확인을 위해 JSON 형태로 출력
			// 단기 예보 호출
			// /getVilageFcst
			String VilageFcst = SSWeather("/getVilageFcst", baseDate, nx, ny);
			System.out.println("단기 예보");
//			System.out.println(VilageFcst);
//			jsonToDto(VilageFcst, "단기 예보");
			
			return weatherMap;
		
	}
	
	// 초단기 실황 데이터를 반환하는 메소드
	public List<WeatherDto> getNcstInfo(String nx, String ny) throws Exception {
		System.out.println("WeatherApi.apiTest() 호출");
		String requestUrl = "/getUltraSrtNcst";
		List<WeatherDto> weatherList = new ArrayList<>();
		
		LocalDateTime now = LocalDateTime.now();
		String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String baseTime = now.format(DateTimeFormatter.ofPattern("HHmm"));
			// 초단기 실황 호출
			// /getUltraSrtNcst
			String ultraSrtNcst = SSWeather("/getUltraSrtNcst", baseDate, nx, ny);
			System.out.println("초단기 실황");
			System.out.println(ultraSrtNcst);
			
			weatherList = jsonToDto(ultraSrtNcst, "초단기 실황");
			
			
			return weatherList;
		
	}
	
	
	// 단기 예보 데이터를 반환하는 메소드
	public Map<String, List<WeatherDto>> getFcstInfo(String nx, String ny) throws Exception {
		System.out.println("WeatherApi - getFcstInfo() 호출");
		String requestUrl = "/getUltraSrtNcst";
		List<WeatherDto> weatherList = new ArrayList<>();
		String requestType = "Fcst";
		if(requestType.equals("Fcst")) {
			requestUrl = "/getUltraSrtFcst";
		}
		LocalDateTime now = LocalDateTime.now();
		String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String baseTime = now.format(DateTimeFormatter.ofPattern("HHmm"));
			// 초단기 예보 호출
			// /getUltraSrtFcst
			String ultraSrtFcst = SSWeather("/getUltraSrtFcst", baseDate, nx, ny);
			System.out.println("초단기 예보");
//			System.out.println(ultraSrtFcst);
			
			// JSON > DtoList
			jsonToDto(ultraSrtFcst, "초단기 예보");
			
			// 날짜 + 시간 별로 정렬
			
			
//			System.out.println(new Gson().toJson(weatherMap)); // 데이터 변환 확인을 위해 JSON 형태로 출력
			// 단기 예보 호출
			// /getVilageFcst
			String VilageFcst = SSWeather("/getVilageFcst", baseDate, nx, ny);
			System.out.println("단기 예보");
//			System.out.println(VilageFcst);
			weatherList = jsonToDto(VilageFcst, "단기 예보");
			Map<String, List<WeatherDto>> weatherMap = collectByDate(weatherList);
			
			return weatherMap;
	}
	
	
	/* 초단기, 단기 예보 데이터 변환 Method */
	private Map<String, List<WeatherDto> > collectByDate(List<WeatherDto> list) {
		//  key   , value
		// { "name" : "ABCD", "address" : "인천" } << JSON 형태
		Map<String, List<WeatherDto>> weatherMap = new LinkedHashMap<>();
		for(WeatherDto dto : list) {
			String mapKey = dto.getFcstDate() + dto.getFcstTime(); // 예측일자 + 예측시간 : key
//			System.out.println(mapKey);
			List<WeatherDto> dateList; // 날씨별로 정리할 날씨정보 목록
			if(weatherMap.containsKey(mapKey)) { // key가 존재하면 true 없으면 false 반환
				dateList = weatherMap.get(mapKey);
			}else {
				dateList = new ArrayList<>();
			}
			dateList.add(dto); // key에 해당하는 날씨정보 추가
			
			weatherMap.put(mapKey, dateList); // map에 key와 value 추가 (덮어쓰기)
		}
		return weatherMap;
	}
	
	
	private List<WeatherDto> jsonToDto(String jsonData, String dataType) {
		List<WeatherDto> weatherList = new ArrayList<>();
		JsonArray items = JsonParser.parseString(jsonData).getAsJsonObject()
				.get("response").getAsJsonObject()
				.get("body").getAsJsonObject()
				.get("items").getAsJsonObject()
				.get("item").getAsJsonArray();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		for(JsonElement item : items) {
			WeatherDto dto = gson.fromJson(item, WeatherDto.class);
			dto.setDataType(dataType);
			if(!dataType.equals("초단기 실황")) {
			dto.codeToName(dto.getCategory(), dto.getFcstValue());
			}
			weatherList.add(dto);
		}
		return weatherList;
	}
	
	public String SSWeather(String url, String baseDate, String nx, String ny) throws Exception{
		String result = "";
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		    ClassicHttpRequest httpGet = ClassicRequestBuilder.get("https://apihub.kma.go.kr/api/typ02/openApi/VilageFcstInfoService_2.0" + url)
		            .addParameter("pageNo", "1")
		            .addParameter("numOfRows", "1000")
		            .addParameter("dataType", "JSON")
		            .addParameter("base_date", baseDate)
		            .addParameter("base_time", "0500")
		            .addParameter("nx", nx)
		            .addParameter("ny", ny)
		            .addParameter("authKey", "UjSqsQsvS0C0qrELL8tAYQ")
		    		.build();
		    result = httpclient.execute(httpGet, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity1 = response.getEntity();
		        String resDate = EntityUtils.toString(entity1);
		        EntityUtils.consume(entity1);
		        return resDate;
		    });
		}
		return result;
	}
	
}
