package khc.arranged.dependency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ModelViewController {
	
	// 페이지(view) 데이터(model)을 같이 응답
	@GetMapping("/요청URL")
	public String pageAndData(Model model) {
		model.addAttribute("이름", "값"); // 응답 데이터
		return "home"; // 응답 페이지
	}
	
	@GetMapping("/mavurl")
	public ModelAndView modelAndView() { // 응답 데이터와 페이지를 ModelAndView 오브젝트를 통해 한 번에 응답
//		ModelAndView mav = new ModelAndView("home"); // 응답 페이지
		ModelAndView mav = new ModelAndView(); // 빈 오브젝트 생성
		mav.setViewName("home"); // 응답 페이지
		mav.addObject("이름", "값"); // 응답 데이터
		return mav;
	}
	
	// 요청에 파라미터가 있는 경우 (로그인 요청, 글 조회 요청 등등)
	@GetMapping("/요청URL2")
	public String requestWithParameter(@RequestParam(value="요청파라미터1") String paramName1,
									   @RequestParam(value="요청파라미터2") String paramName2) {		
		
		return "폴더/페이지이름";
	}
	@GetMapping("/요청URL3")
	public String requestWithParameter2(@RequestParam(value="요청파라미터1") String paramName1,
									    @RequestParam(value="요청파라미터2") String paramName2, Model model) {		
		
		return "폴더/페이지이름";
	}
	
}
