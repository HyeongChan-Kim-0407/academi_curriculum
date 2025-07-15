package khc.springboot.project1.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		System.out.println("메인페이지 이동 요청");
		return "home"; //home.html
	}
	
}
