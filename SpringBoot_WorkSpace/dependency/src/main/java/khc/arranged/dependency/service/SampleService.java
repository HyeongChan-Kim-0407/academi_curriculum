package khc.arranged.dependency.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import khc.arranged.dependency.domain.SampleEntity;
import khc.arranged.dependency.dto.City;
import khc.arranged.dependency.repository.SampleRepository;

@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;
	
	// 기능 메소드 작성
	public void sampleMethod(String username, String userpass) {
		System.out.println("8. 서비스의 기능 메소드 작성");
		// 9. Entity 객체 생성
		SampleEntity sampleEntity = new SampleEntity(username, userpass);
		// 10. Entity 객체를 Repository에 save
		sampleRepository.save(sampleEntity);
	}

	// 목록을 조회하고 반환하는 메소드
	public List<SampleEntity> listMethod() {
		// 5. Repository에서 SampleEntity 전체 목록 조회
		List<SampleEntity> entityList = sampleRepository.findAll();
		// 6. 조회된 SampleEntity 전체 목록 반환
		return entityList;
	}

	public List<SampleEntity> searchMethod(String searchName) {
		return sampleRepository.findByUsername(searchName);
	}
	
	public String responseCityCodeList() throws IOException {
    	String data = null;
    	try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
    	    ClassicHttpRequest httpGet = ClassicRequestBuilder.get("http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getCtyCodeList")
    	    		.addParameter("serviceKey", "zNIoxf23wBzw9HyE+jXx+71VpwT1IQmL+Rw7XgjGwZN5RwDqAA9oKr/zayFyWSaxujVkPSA0Cqhy5AcGZ2zudw==")
    	    		.addParameter("_type", "json")
    	            .build();
    	    data = httpclient.execute(httpGet, response -> {
    	        System.out.println(response.getCode() + " " + response.getReasonPhrase());
    	        final HttpEntity entity1 = response.getEntity();
    	        String resData = EntityUtils.toString(entity1);
    	        EntityUtils.consume(entity1);
    	        return resData;
    	    });
    	} 
    	return data;
    }
	
	public List<City> getCityCodeList() {
		
		try {
			String response = responseCityCodeList();
			System.out.println(response);
			JsonObject resObj = JsonParser.parseString(response).getAsJsonObject(); // Element >> Object {}, Array [] 변환
			System.out.println(resObj);
			JsonObject key_response = resObj.get("response").getAsJsonObject();
			JsonObject body = key_response.get("body").getAsJsonObject();
			JsonObject items = body.get("items").getAsJsonObject();
			JsonArray item = items.get("item").getAsJsonArray();
			List<City> cityList = new ArrayList<>();
			for(JsonElement itemElement : item) {
				JsonObject itemObj = itemElement.getAsJsonObject();
				String citycode = itemObj.get("citycode").getAsString();
				String cityname = itemObj.get("cityname").getAsString();
				City city = new City();
				city.setCityCode(citycode);
				city.setCityName(cityname);
				cityList.add(city);
			}
			return cityList;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}	
}
