package khc.arranged.dependency.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import khc.arranged.dependency.service.ServiceClass;

@Controller
public class HomeController {
	
//	@RequiredArgsConstructor // 클래스에 생성된 모든 필드를 매개변수로 하는 생성자를 자동으로 생성
//	private final ServiceClass sc; // RequiredArgsConstructor 사용 시 final 필드로 초기화 필요
//	public HomeController(ServiceClass sc){
//	this.sc = sc;
//	}
	
	@Autowired
	private ServiceClass sc; // 서비스 클래스 선언
	
	public void methodTest() {
		// ServiceClass에 정의된 method1() 호출
		// ServiceClass 객체 생성
		ServiceClass sc = new ServiceClass();
		// ServiceClass 객체를 통해서 method1() 호출
		sc.method1();
	}
	
	/* 페이지(view)를 응답하는 요청 */
//	@GetMapping("요청URL") String 메소드(){}
//	@PostMapping("요청URL") String 메소드(){}
	@GetMapping("/RequestURL")
	public String methodName(@RequestParam("파라메터명") String 파라메터이름, Model model) {
		String data = sc.method2(); // 서비스 클래스 메소드 호출
		model.addAttribute("data", data); // 응답데이터 저장
		return "페이지명"; // 응답 페이지 지정
	}
	
	@GetMapping("/")
	public String home(Model model) {
		sc.method1(); // 서비스 클래스 메소드 호출
		String data = sc.method2(); // 서비스 클래스 메소드 호출
		model.addAttribute("data", data); // 응답데이터 저장
		return "home"; // src/main/resources/templates/home.html 페이지 응답
	}
	
	@GetMapping("/home")
	public void testMethod() {
		System.out.println("home");
	}
	
	/* 데이터(Model)만 응답하는 요청 */
	// @GetMapping("요청URL") @ResponseBody 리턴타입 메소드(){}
	@GetMapping("/getURL1")
	@ResponseBody
	public String getDATA1() {
		return "data1"; // 문자열 "data1"을 응답
	}
	
	@GetMapping("/getURL2")
	@ResponseBody
	public List<String> getDATA2() {
		List<String> dataList = new ArrayList<>();
		dataList.add("a");
		dataList.add("b");
		return dataList; // 리스트 데이터 dataList를 응답
	}
	
}
