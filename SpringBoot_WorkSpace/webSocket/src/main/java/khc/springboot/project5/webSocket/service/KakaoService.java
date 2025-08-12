package khc.springboot.project5.webSocket.service;

import java.util.Arrays;

import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import khc.springboot.project5.webSocket.dto.KakaoUserDto;

@Service
public class KakaoService {
	
	
	public KakaoUserDto getKakaoUserInfo(String code) throws Exception {
		// 인가 코드(code)로 인증 토큰 요청
		String tokenResponse = getAuthToken(code);
		System.out.println("인증 토큰 응답 : " + tokenResponse);
		// accessToken 추출
		String accessToken = JsonParser.parseString(tokenResponse).getAsJsonObject()
									   .get("access_token").getAsString();
		// 인증 토큰으로 사용자 정보 조회 요청
		String userInfoResponse = getUserInfo(accessToken);
		// 응답 데이터 (사용자 정보)를 KakaoUserDto로 변환 후 리턴
		JsonObject userInfoObj = JsonParser.parseString(userInfoResponse).getAsJsonObject();
		String id = userInfoObj.get("id").getAsString();
		
		JsonObject properties = userInfoObj.get("properties").getAsJsonObject();
		String nickname = properties.get("nickname").getAsString();
		String profileImage = properties.get("profile_image").getAsString();
		
		KakaoUserDto userDto = new KakaoUserDto();
		userDto.setUserId(id);
		userDto.setNickname(nickname);
		userDto.setProfileImage(profileImage);
		return userDto;
	}
	
	@Value("${kakaoApiKey}")
	private String kakaoApiKey;
	
	@Value("${redirectUri}")
	private String RedirectUri;
	
	// 카카오 인증 토큰 요청
	private String getAuthToken(String code) throws Exception {
		System.out.println("Code:"+code);
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		    ClassicHttpRequest httpPost = ClassicRequestBuilder.post("https://kauth.kakao.com/oauth/token")
		            .setEntity(new UrlEncodedFormEntity(Arrays.asList( 
		                    new BasicNameValuePair("grant_type", "authorization_code"),
		                    new BasicNameValuePair("client_id", kakaoApiKey),
		                    new BasicNameValuePair("redirect_uri", RedirectUri),
		                    new BasicNameValuePair("code", code))))
		            .build();
		    
		    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		    
		    String data = httpclient.execute(httpPost, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity = response.getEntity();
		        String responseData = EntityUtils.toString(entity);
		        EntityUtils.consume(entity);
		        return responseData;
		    });
		    return data;
		} 
	}
	
	private String getUserInfo(String accessToken) throws Exception {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		    ClassicHttpRequest httpPost = ClassicRequestBuilder.post("https://kapi.kakao.com/v2/user/me")
		            .build();
		    
		    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		    httpPost.addHeader("Authorization", "Bearer " + accessToken);
		    
		    String data = httpclient.execute(httpPost, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity = response.getEntity();
		        String responseData = EntityUtils.toString(entity);
		        EntityUtils.consume(entity);
		        return responseData;
		    });
		    return data;
		}
	}

}
