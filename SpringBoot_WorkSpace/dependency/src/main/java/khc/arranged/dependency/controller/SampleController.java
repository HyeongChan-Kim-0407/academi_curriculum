package khc.arranged.dependency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import khc.arranged.dependency.domain.SampleEntity;
import khc.arranged.dependency.dto.City;
import khc.arranged.dependency.service.SampleService;

@Controller
public class SampleController {
	
	@Autowired
	private SampleService sampleService;
	
	// 2. "/sample" (get) 요청을 응답하는 메소드 작성
	@GetMapping("/sample")
	public String sampleGet() {
		
		return "sample";
	}
	
	// 5. "/sample" (post) 요청을 응답하는 메소드 작성
	@PostMapping("/sample")
	public String samplePost(@RequestParam("username") String username, @RequestParam("userpass") String userpass) {
		System.out.println("6. 파라미터 확인");
		System.out.println("username : " + username);
		System.out.println("userpass : " + userpass);
		// 7. 서비스의 기능 메소드 호출 (DB 저장 기능)
		sampleService.sampleMethod(username, userpass);
		return "home"; // 11. 응답 페이지
	}
	
	/* 목록 조회 */
	// 2. "/list" (get) 요청을 응답하는 메소드 작성
	@GetMapping("/list")
	public String listGet(Model model) {
		// 3. 서비스 목록 조회 기능 호출
		List<SampleEntity> entityList = sampleService.listMethod();
		// 7. model에 목록 데이터를 저장
		model.addAttribute("entityList", entityList);
		return "list";
	}
	
	@PostMapping("/search")
	public String searchGet(@RequestParam("searchName") String searchName, Model model) {
		List<SampleEntity> entityList = sampleService.searchMethod(searchName);
		model.addAttribute("entityList", entityList);
		return "list";
	}
	
	@GetMapping("/sampleApi")
	public String sampleApi(Model model) {
		List<City> cityList = sampleService.getCityCodeList();
		model.addAttribute("cityList", cityList);
		return "sampleApi";
	}
	
	@GetMapping("/user")
	public String userGet() {
		return "user";
	}
	
	@GetMapping("/admin")
	public String adminGet() {
		return "admin";
	}
	
	
}
