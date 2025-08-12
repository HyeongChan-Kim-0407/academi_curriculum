package khc.springboot.project5.webSocket.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project5.webSocket.dto.KakaoUserDto;
import khc.springboot.project5.webSocket.service.KakaoService;

@Controller
public class HomeController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private KakaoService kakaoservice;
	
	@Value("${kakaoApiKey}")
	private String kakaoApiKey;
	
	@Value("${redirectUri}")
	private String RedirectUri;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/joinChat")
	public String joinChat(@RequestParam("username") String username) {
		session.setAttribute("username", username);
		return "chat";
	}
	
	@GetMapping("/kakaoLogin")
	public String kakaoLogin() {
		
		System.out.println("kakao 인가코드 요청");
		String request_url = "https://kauth.kakao.com/oauth/authorize";
		
		return "redirect:" + request_url
				+ "?client_id=" + kakaoApiKey
				+ "&redirect_uri=" + RedirectUri
				+ "&response_type=code";
	}
	
	@GetMapping("/loginByKakao")
	public String loginByKakao(@RequestParam("code") String code) {
		System.out.println("인가코드 : " + code);
		
		try {
			KakaoUserDto userDto = kakaoservice.getKakaoUserInfo(code);
			session.setAttribute("username", userDto.getNickname());
			return "chat";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
}
