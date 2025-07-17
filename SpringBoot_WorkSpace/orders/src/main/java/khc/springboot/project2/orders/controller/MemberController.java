package khc.springboot.project2.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import khc.springboot.project2.orders.dto.MemberDto;
import khc.springboot.project2.orders.dto.MemberForm;
import khc.springboot.project2.orders.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private HttpSession session;

	@Autowired
	private MemberService membersvc;

	@GetMapping("/join")
	public String joinPage(Model model) {
		System.out.println("/member/join - 회원 가입 페이지 이동 요청");
		model.addAttribute("memberForm", new MemberForm());
		return "member/join";
	}

	@PostMapping("/join")
	public String join(@Valid MemberForm memberForm, AbstractBindingResult bindingResult, Model model, RedirectAttributes ra) {
		System.out.println("/member/join(post) - 회원 가입 요청");
		System.out.println(memberForm);
		// memberForm 객체 > 페이지에서 입력한 회원가입 정보
		// 유효성 검사 이후에 에러가 있다면 중단 후 회원가입 페이지로 이동
		
		if(bindingResult.hasErrors()) {
			System.out.println("유효성 검사 실패");
			model.addAttribute("memberForm", memberForm);
			return "member/join";
		}
		
		try {
		membersvc.joinMember(memberForm);
		System.out.println("회원 가입 성공");
		ra.addFlashAttribute("msg", "회원 가입이 완료되었습니다.");
		} catch (Exception e) {
			System.out.println("회원 가입 실패: " + e.getMessage());
			ra.addFlashAttribute("msg", "회원 가입에 실패하였습니다.");
		}

		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		System.out.println("/member/login (get) - 로그인 페이지 이동 요청");
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("mid") String mid, @RequestParam("mpw") String mpw) {
		System.out.println("/member/login (post) - 로그인 요청");
		
		MemberDto memberdto = membersvc.findByMidAndMpw(mid, mpw);
		
		if(memberdto != null) {
			System.out.println("로그인 성공");
			session.setAttribute("loginId", memberdto.getMid());
			return "redirect:/";
		} else {
			System.out.println("로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
			return "redirect:/member/join"; // 로그인 실패 시 회원 가입 페이지로 리다이렉트
		}
		
	}
	
	@GetMapping("/logout")
	public String logout() {
		System.out.println("/member/logout(get) - 로그아웃 요청");
		session.removeAttribute("loginId"); // 세션에서 로그인 정보 제거
		return "redirect:/";
	}
	
}
