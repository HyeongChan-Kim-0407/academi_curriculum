package khc.springboot.project4.tagoAPI.apiService;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.stereotype.Component;

@Component
public class TagoRequestService {
	
	/* 공공데이터 포털 API(TAGO) */
	private final String DPKEY = "zNIoxf23wBzw9HyE+jXx+71VpwT1IQmL+Rw7XgjGwZN5RwDqAA9oKr/zayFyWSaxujVkPSA0Cqhy5AcGZ2zudw==";
	// 좌표기반 버스 정류소 목록 조회
	public String getStationListByGps(String lat, String lng) {
		// API 요청 > 응답 데이터 반환
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		    ClassicHttpRequest httpGet = ClassicRequestBuilder.get("http://apis.data.go.kr/1613000/BusSttnInfoInqireService/getCrdntPrxmtSttnList")
		    		.addParameter("serviceKey", DPKEY)
		    		.addParameter("numOfRows", "20")
		    		.addParameter("_type", "json")
		    		.addParameter("gpsLati", lat)
		    		.addParameter("gpsLong", lng)
		            .build();
		    String data = httpclient.execute(httpGet, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity1 = response.getEntity();
		        String resData = EntityUtils.toString(entity1);
		        EntityUtils.consume(entity1);
		        return resData;
		    });
		    return data;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public String getBusListByStation(String citycode, String nodeid) {
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		    ClassicHttpRequest httpGet = ClassicRequestBuilder.get("http://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList")
		    		.addParameter("serviceKey", DPKEY)
		    		.addParameter("numOfRows", "20")
		    		.addParameter("_type", "json")
		    		.addParameter("cityCode", citycode)
		    		.addParameter("nodeId", nodeid)
		            .build();
		    String data = httpclient.execute(httpGet, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity1 = response.getEntity();
		        String resData = EntityUtils.toString(entity1);
		        EntityUtils.consume(entity1);
		        return resData;
		    });
		    return data;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
