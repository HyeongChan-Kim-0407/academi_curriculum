package khc.arranged.dependency.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;

public class ApiSample {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/BusRouteInfoInqireService/getRouteInfoIem"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=zNIoxf23wBzw9HyE%2BjXx%2B71VpwT1IQmL%2BRw7XgjGwZN5RwDqAA9oKr%2FzayFyWSaxujVkPSA0Cqhy5AcGZ2zudw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*데이터 타입(xml, json)*/
        urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode("25", "UTF-8")); /*도시코드 [상세기능4. 도시코드 목록 조회]에서 조회 가능*/
        urlBuilder.append("&" + URLEncoder.encode("routeId","UTF-8") + "=" + URLEncoder.encode("DJB30300004", "UTF-8")); /*노선ID [상세기능1. 노선번호목록 조회]에서 조회 가능*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
    
    public String requestGet() throws IOException {
    	String data = null;
    	try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
    	    ClassicHttpRequest httpGet = ClassicRequestBuilder.get("API요청주소")
    	    		.addParameter("요청파라미터1", "값1")
    	    		.addParameter("요청파라미터2", "값2")
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
    
    public String requestPost() throws IOException{
    	String data = null;
    	try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
    	    ClassicHttpRequest httpPost = ClassicRequestBuilder.post("http://httpbin.org/post")
    	            .setEntity(new UrlEncodedFormEntity(Arrays.asList(
    	                    new BasicNameValuePair("요청파라미터1", "값1"),
    	                    new BasicNameValuePair("요청파라미터2", "값2"))))
    	            .build();
    	    data = httpclient.execute(httpPost, response -> {
    	        System.out.println(response.getCode() + " " + response.getReasonPhrase());
    	        final HttpEntity entity = response.getEntity();
    	        String resData = EntityUtils.toString(entity);
    	        EntityUtils.consume(entity);
    	        return resData;
    	    });
    	}
    	return data;
    }
    
}
