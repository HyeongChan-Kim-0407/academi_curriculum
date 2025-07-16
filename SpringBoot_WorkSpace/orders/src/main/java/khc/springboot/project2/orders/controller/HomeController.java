package khc.springboot.project2.orders.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		System.out.println("메인 페이지 이동 요청");
		// 메인 페이지로 이동
		return "home"; // home.html 파일을 찾아서 렌더링	
	}	
	
}
