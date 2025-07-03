package ykd.members.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ykd.members.dto.Member;
import ykd.members.service.MemberService;
  
@Controller
public class HomeController {
	
	@Autowired
	private MemberService memsvc;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("/(get) 메인페이지 이동 요청");
		
		return "home";
	}
	@GetMapping("/join")
	public String joinForm() {
		System.out.println("join(get) 회원가입 페이지 이동요청");
		return "joinForm"; // WEB-INF/views/joinForm.jsp  페이지
	}
	@PostMapping("/join")
	public String memberJoin(Member member ,Model model) {
		System.out.println("join(post) 회원가입 요청");
		System.out.println(member);
		try {
			memsvc.registMember(member);
			return "redirect:/"; // 메인 페이지로 이동요청
		} catch (Exception e) {
			model.addAttribute("joinMember", member); // 사용자가 입력한 회원정보
			return "joinForm"; // joinForm.jsp 
		}
	}
	@GetMapping("/login")
	public String loginForm() {
		System.out.println("/login(get) 로그인 페이지 이동요청");
		return "loginForm"; // WEB-INF/views/loginForm.jsp  페이지
	}
	@PostMapping("/login")
	public String memberLogin(String mid, String mpw, RedirectAttributes ra) {
		System.out.println("/login(post) 로그인 요청");
		System.out.println("입력한 아이디 : " + mid);
		System.out.println("입력한 비밀번호 : " + mpw);
		/* 서비스 - 회원 정보 조회 메소드 호출 */
		Member member = memsvc.loginMember(mid, mpw);
		if(member != null) {
			//회원정보가 조회 되는 경우 
			System.out.println("성공");
			//로그인 처리
			session.setAttribute("loginMid", member.getMid());
			ra.addFlashAttribute("resultMsg", "로그인 되었습니다.");
			return "redirect:/";  //메인 페이지로 이동요청
		} else {
			//회원정보가 조회 되지 않는 경우
			System.out.println("실패");
			ra.addFlashAttribute("resultMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			return "redirect:/login"; //로그인 페이지로 요청
		}
	}
	@GetMapping("logout")
	public String memberLogout() {
		
		session.removeAttribute("loginMid");
		return "redirect:/";
	}

	@GetMapping("/myInfo")
	public String myInfo(Model model) {
		System.out.println("/myInfo(get) 내 정보 페이지 이동요청");
		/* 1. 로그인 된 회원의 아이디 확인 */
		/* 세션에 저장된 loginMid 값 */
		String id = (String) session.getAttribute("loginMid");
		if(id == null) { // 로그인 되지 않은 경우
			System.out.println("잘못된 접근. 로그인 되어 있지 않음.");
			return "loginForm";
		}
		/* 2. 회원아이디로 회원정보 조회 - service의 회원 정보 조회 기능 호출 */
		Member member = memsvc.findByMid(id);
		System.out.println(member);
		/* 3. 조회된 회원정보와 페이지를 응답 */
		model.addAttribute("minfo", member);
		return "myInfo";
	}
	
	@GetMapping("/memberList")
	public String memberList(Model model) {
		System.out.println("/memberList(get) 회원 목록 페이지 이동요청");
		/* 1. 회원 목록 조회 - service의 회원목록 조회 기능 호출 */
		ArrayList<Member> memberList = memsvc.findMemberList();
		/* 2. 조회된 회원 목록을 Model에 저장 */
		model.addAttribute("mList", memberList);
		/* 3. memberList.jsp 응답 */
		return "memberList"; // memberList.jsp
	}

	@GetMapping("/deleteMember")
	public String deleteMember(String delmid) {
		System.out.println("/deleteMember(get) 회원 삭제 요청");
		System.out.println("삭제할 회원 아이디 : "+delmid);
		/* 회원 삭제 처리 */
		memsvc.deleteMemberByMid(delmid);
		/* 회원 목록 페이지로 이동 */
		return "redirect:/memberList";
	}
	
	
	@GetMapping("/modify")
	public String memberModifyForm(Model model) {
		System.out.println("/modify(get) 회원정보 수정 페이지 요청");
		/* 1. 로그인 된 회원의 아이디 확인 */
		/* 세션에 저장된 loginMid 값 */
		String id = (String) session.getAttribute("loginMid");
		if(id == null) { // 로그인 되지 않은 경우
			System.out.println("잘못된 접근. 로그인 되어 있지 않음.");
			return "redirect:/login";
		}		
		model.addAttribute("member", memsvc.findByMid(id));
		return "modifyForm";
	}
	
	
	@PostMapping("/modify")
	public String memberModify(Member member) {
		System.out.println("/modify(post) 회원정보 수정 요청");
		// member >> 새 비밀번호, 새 이름, 새 생년월일
		/* 세션에 저장된 loginMid 값 */
		String id = (String) session.getAttribute("loginMid");
		if(id == null) { // 로그인 되지 않은 경우
			System.out.println("잘못된 접근. 로그인 되어 있지 않음.");
			return "redirect:/login";
		}
		member.setMid(id); // 정보를 수정할 회원의 아이디
		/* 회원정보 수정 처리 */
		int update = memsvc.updateMemberInfo(member);
		/* 로그인 상태 해제 - 로그아웃처리 */
		session.removeAttribute("loginMid");
		/* 로그인 페이지로 이동요청 */
		return "redirect:/login";
	}
}




















