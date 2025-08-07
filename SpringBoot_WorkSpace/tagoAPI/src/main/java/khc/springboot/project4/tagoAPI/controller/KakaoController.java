package khc.springboot.project4.tagoAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project4.tagoAPI.dto.KakaoUserDto;
import khc.springboot.project4.tagoAPI.service.KakaoService;

@Controller
@RequestMapping("/kakao")
public class KakaoController {
	
	@Value("${kakaoApiKey}")
	private String kakaoApiKey;
	
	@Value("${kakaoRedirectUri}")
	private String kakaoRedirectUri;
	
	@Autowired
	private KakaoService kakaoService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	public String kakaoLogin() {
		System.out.println("kakao 인가코드 요청");
		
		// 카카오 로그인 요청 주소
		String request_url = "https://kauth.kakao.com/oauth/authorize";
		// 
		String response_type = "code";
		  
		return "redirect:"+request_url
				+"?client_id="+kakaoApiKey
				+"&redirect_uri="+kakaoRedirectUri
				+"&response_type="+response_type;
	}
	
	@GetMapping("/authCode")
	public String kakaoAuthCode(@RequestParam("code") String code) {
		System.out.println("인가코드 : " + code);
		// 인가 코드로 인증 토큰 요청
		// 인증 토큰으로 사용자 정보 조회
		KakaoUserDto loginUser = kakaoService.getKakaoUserInfo(code);
		// 조회된 사용자 정보로 로그인 처리
		session.setAttribute("loginUser", loginUser);
		session.setAttribute("loginId", loginUser.getKakaoid());
		
		return "redirect:/";
	}
	
}
