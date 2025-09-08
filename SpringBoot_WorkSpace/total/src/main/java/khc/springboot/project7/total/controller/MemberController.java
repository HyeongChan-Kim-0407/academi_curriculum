package khc.springboot.project7.total.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import khc.springboot.project7.total.dto.MemberDto;
import khc.springboot.project7.total.dto.MemberForm;
import khc.springboot.project7.total.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/join")
	public String join(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "member/join";
	}
	
	@PostMapping("/join")
	public String joinPost(@Valid MemberForm memberForm, BindingResult bindingResult, Model model, RedirectAttributes ra) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("memberForm", memberForm);
			return "member/join";
		}
		
		try {
			memberService.memgerJoin(memberForm);
			ra.addFlashAttribute("msg", "회원가입 성공");
		} catch (Exception e) {
			ra.addFlashAttribute("msg", "회원가입 실패");
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@PostMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam("mid") String mid) {
		System.out.println("MemberController.checkId() 호출");
		System.out.println("mid: " + mid);
		String checkResult = memberService.checkId(mid);
		return checkResult;
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String loginPost(@RequestParam("mid") String mid, @RequestParam("mpw") String mpw, RedirectAttributes ra) {
		System.out.println("MemberController.loginPost() 호출");
		MemberDto memberDto = memberService.memberLogin(mid, mpw);
		if(memberDto != null) {
			System.out.println("로그인 성공");
			httpSession.setAttribute("loginUser", memberDto);
			ra.addFlashAttribute("msg", "로그인 성공");
			return "redirect:/";
		}
		ra.addFlashAttribute("msg", "로그인 실패");
		return "redirect:/";
	}
	
	@GetMapping("/info")
	public String memberInfo(Model model) {
		System.out.println("MemberController.memberInfo() 호출");
		MemberDto loginUser = (MemberDto) httpSession.getAttribute("loginUser");
		model.addAttribute("loginUser", loginUser);
		
		return "member/info";
	}
	
	@PostMapping("/info")
	public String memberInfoPost(@RequestParam("locations") List<String> locations) {
		System.out.println("MemberController.memberInfoPost() 호출");
		System.out.println("locations: " + locations);
		MemberDto loginUser = (MemberDto) httpSession.getAttribute("loginUser");
		memberService.updateMemberLocations(locations, loginUser);
		
		return "redirect:/member/info";
	}
	
	@GetMapping("/logout")
	public String logout() {
		httpSession.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	
}
