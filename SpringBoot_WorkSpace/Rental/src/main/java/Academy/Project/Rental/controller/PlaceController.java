package Academy.Project.Rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Academy.Project.Rental.dto.PlaceDto;
import Academy.Project.Rental.dto.PlaceForm;
import Academy.Project.Rental.dto.RequestDto;
import Academy.Project.Rental.service.PlaceService;
import Academy.Project.Rental.service.RequestService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/place")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

	@Autowired
	private HttpSession session;

	@Autowired
	private RequestService requestService;

	@GetMapping("/regist")
	public String registPage(Model model) {
		model.addAttribute("placeform", new PlaceForm());
		return "place/regist";
	}

	@PostMapping("/regist")
	public String regist(@Valid @ModelAttribute("placeform") PlaceForm placeform, BindingResult bindingResult,
			Model model, HttpSession session) {
		String Mid = (String) session.getAttribute("loginMid");
		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 실패");
			return "place/regist";
		}
		try {
			placeService.regist(placeform, Mid);
		} catch (Exception e) {
			System.out.println("대여등록 실패: " + e.getMessage());
			e.printStackTrace();
		}
		String dongName = (String) session.getAttribute("dongName");
		if (dongName != null && !dongName.isEmpty()) {
			return "redirect:/?dongName=" + dongName;
		} else {
			return "redirect:/";
		}
	}

	@GetMapping("/list")
	public String placeList(Model model, @RequestParam(name = "ptype", required = false) String ptype) {
		System.out.println("시설 목록 페이지 이동 요청");
		System.out.println(ptype);
		List<PlaceDto> placeList;
		if (ptype == null) { // 모두 보기 인 경우
			// 전체 시설 목록 조회
			placeList = placeService.findPlaceListAll();
		} else { // 모두 보기가 아닌 경우
			// 시설 목록 조회
			placeList = placeService.findPlaceList(ptype);
		}
		// model에 시설 목록 저장
		model.addAttribute("pdList", placeList);
		return "place/list";
	}

	@GetMapping("/view/{id}")
	public String placeView(@PathVariable("id") Long id, Model model) {
		System.out.println("시설 목록 상세 페이지 이동 요청");

		PlaceDto placeDto = placeService.findPlaceById(id);

		List<RequestDto> rdList = requestService.findRequestByPlaceId(id);

		model.addAttribute("placeDto", placeDto);
		model.addAttribute("rdList", rdList);
		return "place/view";
	}

	@GetMapping("/search")
	public String searchTitle(@RequestParam("ptitle") String ptitle, @RequestParam("ptype") String ptype,
			@RequestParam("plocation") String plocation, Model model) {
		List<PlaceDto> placeList;
		placeList = placeService.findsearch(ptitle, ptype, plocation);
		model.addAttribute("pdList", placeList);
		return "place/list";
	}
}
