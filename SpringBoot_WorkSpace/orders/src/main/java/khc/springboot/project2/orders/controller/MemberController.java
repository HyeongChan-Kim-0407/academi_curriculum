package khc.springboot.project2.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
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
	public String joinPage() {
		System.out.println("/member/join - 회원 가입 페이지 이동 요청");
		return "member/join";
	}

	@PostMapping("/join")
	public String join(MemberForm memberForm) {
		System.out.println("/member/join(post) - 회원 가입 요청");
		System.out.println(memberForm);
		
		try {
		membersvc.joinMember(memberForm);
		System.out.println("회원 가입 성공");
		} catch (Exception e) {
			System.out.println("회원 가입 실패: " + e.getMessage());
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
	
}
