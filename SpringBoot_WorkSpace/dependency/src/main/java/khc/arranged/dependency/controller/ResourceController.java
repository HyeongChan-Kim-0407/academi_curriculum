package khc.arranged.dependency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import khc.arranged.dependency.domain.EntityClass;
import khc.arranged.dependency.service.ResourceService;

@Controller
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	// 메인페이지 이동요청 >> 메인페이지로 응답
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("data1", "테스트데이터1");
		return "home";
	}
	
	@GetMapping("/requestURL")
	public String responseMethod() {
		return "responsePage";
	}
	
	@GetMapping("/requestParameter")
	public String responseMethod2(@RequestParam("pname") String pname, Model model) {
		// 데이터를 조회해서 - service
		List<EntityClass> entityList = resourceService.getDataList(pname);
		// 조회된 데이터를 모델에 저장
		model.addAttribute("entityList", entityList);
		return "responsePage";
	}
	
}
