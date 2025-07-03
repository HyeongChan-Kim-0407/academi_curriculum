package member.khc.controller;

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

import member.khc.dto.Member;
import member.khc.service.MemberService;

@Controller
public class HomeController {

	@Autowired
	private MemberService msvc;
	
	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("/(get) 메인페이지 이동요청");
		return "home";
	}

	@GetMapping("/join") // value 생략
	public String joinForm() {
		System.out.println("join(get) 회원가입 페이지 이동");
		return "joinForm"; // WEB-INF/views/joinForm.jsp 페이지 전환
	}

	@PostMapping("/join")
	public String memberJoin(Member member, Model model) {
		System.out.println("join(post) 회원가입 처리 요청");
		// 회원가입 기능 호출 및 결과 확인
		System.out.println(member);
		try {
			// service 클래스의 메소드 호출
			msvc.registMember(member);
			return "redirect:/"; //home.jsp 반환 >> 메인 페이지로 이동요청 지시
			// redirect:재요청 지시 주소
		} catch (Exception e) { 
			// 이전에 입력한 회원가입 정보
			model.addAttribute("joinMember", member); 
			return "joinForm"; //joinForm.jsp >> 회원가입 페이지로 이동요청 지시
		}
	}

	@GetMapping("/login") // value 생략
	public String loginForm() {
		System.out.println("join(get) 로그인 페이지 이동");
		if(session.getAttribute("loginId") == null) {
			return "loginForm"; // WEB-INF/views/loginForm.jsp 페이지 전환
			
		}else {
			return "redirect:/";
		}
		
	}

	@PostMapping("/login")
	public String memberLogin(String mid, String mpw, RedirectAttributes ra) {
		System.out.println("login(post) 로그인 처리 요청");
		System.out.println("아이디 : " + mid + "\n비밀번호 : " + mpw);

		Member loginMember = msvc.loginMember(mid, mpw);

		if (loginMember != null) {
			//회원정보 조회 o
			System.out.println("성공");
			//로그인처리
			 session.setAttribute("loginId", loginMember.getMid());
// Member 객체로도 저장이 가능하나 String 타입이 아닐 시 서버 재시작 과정에서 유실되므로 String으로 저장
			 ra.addFlashAttribute("resultMsg", "로그인 성공");
			//메인페이지로 이동 요청
			return "redirect:/";
		} else {
			//회원정보 조회 x
			System.out.println("실패");
			ra.addFlashAttribute("resultMsg", "아이디 혹은 비밀번호가 일치하지 않습니다");
			//로그인페이지로 이동 요청
			return "redirect:/login";
		}

	}
	
	@GetMapping("/memberInfo")
	public String memberInfo(Model model) {// Controller에서 페이지로 정보를 전송해야 할 때 Model 사용
		System.out.println("/memberInfo(get) 회원정보 페이지 이동요청");
		/* 1. 로그인 된 회원의 아이디 확인 */
		/* 세션에 저장된 loginId 값 */
		String loginId = (String) session.getAttribute("loginId");
		if(loginId == null) { // 로그인 후 일정시간 경과하여 세션이 해제되거나, 로그인을 하지 않은 경우
			// 콘솔에 출력
			System.out.println("잘못된 접근. 로그인 되어 있지 않음.");
			// 홈페이지에 출력하려면 리다이렉트 개념 필요
			return "loginForm";
		}
		/* 2. 회원아이디로 회원정보 조회 */
		Member member = msvc.findByMid(loginId);
		System.out.println(member);		
		/* 3. 조회된 정보와 페이지를 응답 (model.addAttribute) */ 
		model.addAttribute("mInfo", member);
		return "memberInfo";
	}
//	디스패쳐 : 페이지를 직접적으로 반환 ex) memberInfo.jsp
//	리다이렉트 : 클라이언트가 재요청해야할 주소를 반환
	@GetMapping("logout")
	public String memberLogout() {
		System.out.println("/logout(get) 로그아웃 요청");
		session.removeAttribute("loginId");
		return "redirect:/";
		
	}
	
	@GetMapping("/memberList")
	public String memberList(Model model) {
		System.out.println("/memberList(get) 회원목록 페이지 이동 요청");
		/* 1. 회원목록 조회 */
		System.out.println("조회된 목록 출력");
		ArrayList<Member> memberList = msvc.findMemberList();
		/* Model에 조회 결과 저장 */
		model.addAttribute("mList", memberList);
		/* 조회 결과와 페이지 반환 */
		System.out.println(memberList);
		return "memberList"; //memberList.jsp
	}
	
	@GetMapping("/deleteMember")
	public String memberList(String delMid) {
		System.out.println("/deleteMember(get) 회원 삭제 요청");
		System.out.println("삭제할 회원 아이디 : " + delMid);
		
		int delResult = msvc.deleteByMid(delMid);
		
		return "redirect:/memberList";
	}
	
	@GetMapping("/modify")
	public String modifyForm(Model model) {
		System.out.println("/modify(get) 회원정보 수정 페이지 이동");
		
		String loginId = (String) session.getAttribute("loginId");
		
		Member member = msvc.findByMid(loginId);
		
		model.addAttribute("modifyInfo", member);
		return "modifyForm";
	}
	
	@PostMapping("/modify")
	public String modifyMember(Member member) {
		System.out.println("회원정보 수정 처리 요청");

		member.setMid((String) session.getAttribute("loginId"));
		
		int updateResult = msvc.updateMemberInfo(member);
		
		if(updateResult != 1) {
			System.out.println("정보 수정 과정에서 문제 발생");
			return "redirect:/";
		}
		
		System.out.println("회원정보 정상 수정");
		
		
		return "redirect:logout";
	}
	
}
