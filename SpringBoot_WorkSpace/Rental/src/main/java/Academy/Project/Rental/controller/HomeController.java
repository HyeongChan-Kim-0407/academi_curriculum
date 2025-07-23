package Academy.Project.Rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home() {
		System.out.println("메인 페이지 이동요청");
		return "home";
	}
	
	@GetMapping("/map")
	public String map() {
		System.out.println("map 페이지 이동요청");
		return "map";
	}

	// 필요에 따라 수정/삭제 등 추가 가능
}
