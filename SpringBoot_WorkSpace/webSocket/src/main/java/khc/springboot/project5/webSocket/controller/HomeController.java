package khc.springboot.project5.webSocket.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project5.webSocket.domain.Message;
import khc.springboot.project5.webSocket.dto.KakaoUserDto;
import khc.springboot.project5.webSocket.dto.MessageDto;
import khc.springboot.project5.webSocket.repository.ChatRepository;
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
	
	@Autowired
	private ChatRepository chatRepository;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/joinChat")
	public String joinChat(@RequestParam("username") String username, Model model) {
		session.setAttribute("username", username);
		List<Message> messageList = chatRepository.findAllByOrderByIdAsc();
		List<MessageDto> messageDtoList = new ArrayList<>();
		for (Message msg : messageList) {
			MessageDto msgDto = new MessageDto(msg);
			messageDtoList.add(msgDto);
		}
		model.addAttribute("messageList", messageDtoList);
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
	public String loginByKakao(@RequestParam("code") String code, Model model) {
		System.out.println("인가코드 : " + code);
		
		try {
			KakaoUserDto userDto = kakaoservice.getKakaoUserInfo(code);
			session.setAttribute("username", userDto.getNickname());
			List<Message> messageList = chatRepository.findAllByOrderByIdAsc();
			List<MessageDto> messageDtoList = new ArrayList<>();
			for (Message msg : messageList) {
				MessageDto msgDto = new MessageDto(msg);
				messageDtoList.add(msgDto);
			}
			// model에 채팅 내역 담아서 전송
			model.addAttribute("messageList", messageDtoList);
			return "chat";
		} catch (Exception e) {
			e.printStackTrace();
			return "home";
		}
		
	}
	
}
