package khc.springboot.project4.tagoAPI.service;

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

import khc.springboot.project4.tagoAPI.dto.KakaoUserDto;

@Service
public class KakaoService {
	
	@Value("${kakaoApiKey}")
	private String kakaoApiKey;
	
	@Value("${kakaoRedirectUri}")
	private String kakaoRedirectUri;
	
	
	// HttpClient 사용
	// 인가 코드로 인증 토큰 요청 메소드
	public String kakaoRequestToken(String code) {

		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

		    ClassicHttpRequest httpPost = ClassicRequestBuilder.post("https://kauth.kakao.com/oauth/token")
		            .setEntity(new UrlEncodedFormEntity(Arrays.asList(
		                    new BasicNameValuePair("grant_type", "authorization_code"),
		                    new BasicNameValuePair("client_id", kakaoApiKey),
		                    new BasicNameValuePair("redirect_uri", kakaoRedirectUri),
		                    new BasicNameValuePair("code", code))))
		            .build();
		    
		    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		    
		    String data = httpclient.execute(httpPost, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity = response.getEntity();
		        String resData = EntityUtils.toString(entity);
		        EntityUtils.consume(entity);
		        return resData;
		    });
		    return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	// 인증 토큰으로 사용자 정보 조회 메소드
	public KakaoUserDto getKakaoUserInfo(String code) {
		
		String tokenResponse = kakaoRequestToken(code);
		
		System.out.println(tokenResponse);
		
		String accessToken = JsonParser.parseString(tokenResponse).getAsJsonObject().get("access_token").getAsString();
		
		System.out.println(accessToken);
		// 인증 토큰으로 사용자 정보 요청
		String userInfoResponse = kakaoRequestUserInfo(accessToken);
		
		JsonObject userInfoObj = JsonParser.parseString(userInfoResponse).getAsJsonObject();
		
		String kakaoid = userInfoObj.get("id").getAsString();
		System.out.println("kakaoid : " + kakaoid);
		JsonObject properties = userInfoObj.getAsJsonObject("properties");
		String nickname = properties.get("nickname").getAsString();
		System.out.println("nickname : " + nickname);
		String profileImage = properties.get("profile_image").getAsString();
		System.out.println("profileImage : " + profileImage);
		
		KakaoUserDto kakaoUserDto = new KakaoUserDto();
		kakaoUserDto.setKakaoid(kakaoid);
		kakaoUserDto.setNickname(nickname);
		kakaoUserDto.setProfileImage(profileImage);
		
		return kakaoUserDto;
		
	}

	private String kakaoRequestUserInfo(String accessToken) {
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

		    ClassicHttpRequest httpPost = ClassicRequestBuilder.post("https://kapi.kakao.com/v2/user/me")
		            .build();
		    
		    httpPost.addHeader("Authorization", "Bearer " + accessToken);
		    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		    
		    String data = httpclient.execute(httpPost, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity = response.getEntity();
		        String resData = EntityUtils.toString(entity);
		        EntityUtils.consume(entity);
		        return resData;
		    });
		    System.out.println(data);
		    return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
