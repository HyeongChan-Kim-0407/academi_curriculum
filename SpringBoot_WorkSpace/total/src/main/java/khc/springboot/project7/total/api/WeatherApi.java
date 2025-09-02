package khc.springboot.project7.total.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import khc.springboot.project7.total.dto.WeatherDto;

@Component
public class WeatherApi {
	
	
	public List<WeatherDto> apiTest(String baseDate) {
		System.out.println("WeatherApi.apiTest() 호출");
		List<WeatherDto> weatherList = new ArrayList<>();
		try {
			// 초단기 실황 호출
			// /getUltraSrtNcst
			String ultraSrtNcst = SSWeather("/getUltraSrtNcst", baseDate);
			System.out.println("초단기 실황");
//			System.out.println(ultraSrtNcst);
			weatherList = jsonToDto(ultraSrtNcst, "초단기 실황");
			
			// 초단기 예보 호출
			// /getUltraSrtFcst
			String ultraSrtFcst = SSWeather("/getUltraSrtFcst", baseDate);
			System.out.println("초단기 예보");
//			System.out.println(ultraSrtFcst);
//			jsonToDto(ultraSrtFcst, "초단기 예보");
			// 단기 예보 호출
			// /getVilageFcst
			String VilageFcst = SSWeather("/getVilageFcst", baseDate);
			System.out.println("단기 예보");
//			System.out.println(VilageFcst);
//			jsonToDto(VilageFcst, "단기 예보");
			
			
			} catch (Exception e) {
		}
		return weatherList;
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
			weatherList.add(dto);
		}
		return weatherList;
	}
	
	public String SSWeather(String url, String baseDate) throws Exception{
		String result = "";
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		    ClassicHttpRequest httpGet = ClassicRequestBuilder.get("https://apihub.kma.go.kr/api/typ02/openApi/VilageFcstInfoService_2.0" + url)
		            .addParameter("pageNo", "1")
		            .addParameter("numOfRows", "1000")
		            .addParameter("dataType", "JSON")
		            .addParameter("base_date", baseDate)
		            .addParameter("base_time", "0500")
		            .addParameter("nx", "54")
		            .addParameter("ny", "125")
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
