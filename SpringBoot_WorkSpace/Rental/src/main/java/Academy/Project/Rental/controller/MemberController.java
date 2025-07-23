package Academy.Project.Rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Academy.Project.Rental.domain.MemberForm;
import Academy.Project.Rental.dto.MemberDto;
import Academy.Project.Rental.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private HttpSession session;

	@GetMapping("/login")
	public String login() {
		return "member/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("mid") String mid, @RequestParam("mpw") String mpw) {
		MemberDto memberDto = memberService.findByMidAndMpw(mid, mpw);
		if (memberDto != null) {
			session.setAttribute("loginMid", mid);
			return "home";
		} else {

			return "redirect:/member/login";
		}

	}

	@GetMapping("/join")
	public String join(Model model) {
		System.out.println("/member/join(get) 회원가입 페이지 이동 요청");
		model.addAttribute("memberForm", new MemberForm());
		return "member/join";
	}

	@PostMapping("/join")
	public String joinForm(@Valid MemberForm memberForm, AbstractBindingResult bindingResult, Model model,
			RedirectAttributes ra) {
		System.out.println("/member/join(post) 회원가입 요청");

		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 실패");
			model.addAttribute("memberForm", memberForm);
			return "member/join";
		}

		try {
			memberService.join(memberForm);
			System.out.println("회원가입 성공");
			ra.addFlashAttribute("msg", "회원가입이 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("회원가입 실패: " + e.getMessage());
			e.printStackTrace();
			ra.addFlashAttribute("msg", "회원가입에 실패하였습니다.");
		}

		return "redirect:/";
	}

	@PostMapping("/idCheck")
	@ResponseBody
	public String idCheck(@RequestParam("mid") String mid) {
		System.out.println("/member/idCheck(get) 아이디 중복 체크 요청");
		System.out.println("아이디: " + mid);

		MemberDto memberDto = memberService.idCheck(mid);

		if (memberDto == null) {
			return "Y"; // 사용 가능한 아이디
		} else {
			return "N"; // 이미 사용 중인 아이디
		}
	}

}
