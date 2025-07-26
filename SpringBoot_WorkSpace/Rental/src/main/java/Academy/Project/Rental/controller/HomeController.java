package Academy.Project.Rental.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Academy.Project.Rental.domain.Interests;
import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.service.MemberService;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession session;

	@GetMapping("/")
	public String home(Model model) {
		System.out.println("메인 페이지 이동요청");
		String loginMid = (String) session.getAttribute("loginMid");
		System.out.println(loginMid);
		if (loginMid != null) {
			Member loginMember = memberService.findByMid(loginMid);
			
			
			List<Interests> interests = new ArrayList<>();
			String mname = null;
			if (loginMember != null) {
				interests = loginMember.getMinterests();
				mname = loginMember.getMname();
				// 주소에서 행정동 추출
				String address = loginMember.getMaddress();
				String dongName = "";
				if (address != null && !address.isEmpty()) {
					String[] parts = address.split(" ");
					for (int i = parts.length - 1; i >= 0; i--) {
						String part = parts[i];
						if (part.endsWith("구") || part.endsWith("동") || part.endsWith("읍") || part.endsWith("면") || part.endsWith("리")) {
							dongName = part;
							break;
						}
					}
				}
				model.addAttribute("dongName", dongName);
			}

			model.addAttribute("interests", interests);
			model.addAttribute("mname", mname);
			model.addAttribute("mid", loginMid);
			System.out.println("제발 시발");
			return "home";
		} else {

			List<Interests> interests = new ArrayList<>();

			model.addAttribute("interests", interests);

			return "home";
		}
	}

	@GetMapping("/map")
	public String map() {
		System.out.println("map 페이지 이동요청");
		return "map";
	}

	// 필요에 따라 수정/삭제 등 추가 가능
}
