package Academy.Project.Rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Academy.Project.Rental.dto.PlaceDto;
import Academy.Project.Rental.dto.PlaceForm;
import Academy.Project.Rental.service.PlaceService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/place")
public class PlaceController {
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/regist")
	public String registPage(Model model) {
		model.addAttribute("placeform", new PlaceForm());
		return "place/regist";
	}
	
	@PostMapping("/regist")
	public String regist(@Valid @ModelAttribute("placeform") PlaceForm placeform, BindingResult bindingResult,  Model model) {
		
		String Mid = (String)session.getAttribute("loginMid");
		
		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 실패");
			//model.addAttribute("placeform", placeform);
			return "place/regist";
		}
		try {
			placeService.regist(placeform,Mid);
		} catch (Exception e) {
			System.out.println("대여등록 실패: " + e.getMessage());
			e.printStackTrace();
		}
		
		return "home";
	}
	
	@GetMapping("/list")
	public String placeList(Model model) {
		System.out.println("시설 목록 페이지 이동 요청");
		
		List<PlaceDto> pdList = placeService.findPlaceList();
		
		model.addAttribute("pdList", pdList);	
		return "place/list";
	}
}
