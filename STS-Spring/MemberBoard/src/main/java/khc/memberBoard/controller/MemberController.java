package khc.memberBoard.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import khc.memberBoard.dto.Member;
import khc.memberBoard.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService membersvc;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/join")
	public String memberJoinPage(RedirectAttributes ra) {
		System.out.println("/join(get) 회원가입 페이지 이동 요청");
		if(session.getAttribute("loginId") != null) {
			System.out.println("잘못된 접근");
			ra.addFlashAttribute("msg", "잘못된 접근입니다.");
			return "redirect:/";
		}
		return "/member/joinForm";
	}
	
	@PostMapping("/join")
	public String joinMember(Member member, Model model, RedirectAttributes ra) {
		System.out.println("/join(post) 회원가입 처리 요청");
		try {
		membersvc.registMember(member);
		System.out.println("회원가입 성공");
		ra.addFlashAttribute("msg", "회원가입에 성공했습니다.");
		return "redirect:/";
		} catch(Exception e) {
			System.out.println("회원가입 실패");
			model.addAttribute("joinMember", member);
			return "/member/joinForm";
		}
	}
	
	@GetMapping("/login")
	public String memberLoginPage(RedirectAttributes ra) {
		System.out.println("/login(get) 로그인 페이지 이동 요청");
		if(session.getAttribute("loginId") != null) {
			System.out.println("잘못된 접근");
			ra.addFlashAttribute("msg", "잘못된 접근입니다.");
			return "redirect:/";
		}
		return "/member/loginForm";
	}
	
	@PostMapping("/login")
	public String loginMember(String mid, String mpw) {
		System.out.println("/login(post) 로그인 처리 요청");
		System.out.println("아이디 : " + mid + "비밀번호 : " + mpw);
		Member loginMember = membersvc.findMember(mid, mpw);
		
		if(loginMember != null) {
			System.out.println("로그인 성공");
			session.setAttribute("loginId", loginMember.getMid());
			return "redirect:/";
		}else {
			System.out.println("로그인 실패");
			return "redirect:/login";
		}
		
		
		
	}
	
	@GetMapping("/midCheck")
	@ResponseBody // jsp 파일명이 아닌 String 타입의 데이터를 반환
	public String midCheck(String userid) {
		System.out.println("/midCheck(get) 아이디 중복 확인 요청");
		System.out.println("확인할 아이디 : " + userid);
		/* 회원정보 조회 */
		String result = membersvc.idCheck(userid);
		
		return result;
	}
	
	@GetMapping("logout")
	public String logoutMember() {
		
		session.removeAttribute("loginId");
		
		return "redirect:/";
	}
	
	
}
