
package Academy.Project.Rental.controller;



import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Academy.Project.Rental.domain.Interests;
import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.RequestAccept;
import Academy.Project.Rental.dto.MemberDto;
import Academy.Project.Rental.dto.MemberForm;
import Academy.Project.Rental.dto.ReplyForm;
import Academy.Project.Rental.service.MemberService;
import Academy.Project.Rental.service.PlaceService;
import Academy.Project.Rental.service.ReplyService;
import Academy.Project.Rental.service.RequestService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private HttpSession session;

	@Autowired
	private RequestService requestService;

	@Autowired
	private ReplyService replyService;

	@Autowired
	private PlaceService placeService;

	@GetMapping("/login")
	public String login() {
		return "member/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("mid") String mid, @RequestParam("mpw") String mpw, Model model,
			RedirectAttributes rs) {
		MemberDto memberDto = memberService.findByMidAndMpw(mid, mpw);

		if (memberDto != null) {
			session.setAttribute("loginMid", memberDto.getMid());
			session.setAttribute("mname", memberDto.getMname());
			// 주소에서 행정동 추출 (구/동/읍/면/리)
			String address = memberDto.getMaddress();
			String dongName = "";
			if (address != null && !address.isEmpty()) {
				String[] parts = address.split(" ");
				for (int i = parts.length - 1; i >= 0; i--) {
					String part = parts[i];
					if (part.endsWith("구") || part.endsWith("동") || part.endsWith("읍") || part.endsWith("면")
							|| part.endsWith("리")) {
						dongName = part;
						break;
					}
				}
			}
			Member loginMember = memberService.findByMid(mid);
			List<Interests> interests = loginMember.getMinterests();
			model.addAttribute("dongName", dongName);
			model.addAttribute("loginMid", mid);
			model.addAttribute("interests", interests);
			return "redirect:/";
		} else {
			rs.addFlashAttribute("loginError", "아이디 또는 비밀번호가 틀렸습니다.");
			return "redirect:/member/login";
		}
	}

	@GetMapping("/join")
	public String join(Model model) {
		System.out.println("/member/join(get) 회원가입 페이지 이동 요청");
		model.addAttribute("memberForm", new MemberForm());
		return "member/join";
	}

	@GetMapping("/joinForm")
	public String joinForm(@Valid MemberForm memberForm, AbstractBindingResult bindingResult, Model model,
			RedirectAttributes ra, @RequestParam("minterests") String[] minterests) {
		System.out.println("/member/join(post) 회원가입 요청");

		if (bindingResult.hasErrors()) {
			System.out.println("유효성 검사 실패");
			model.addAttribute("memberForm", memberForm);
			return "member/join";
		}

		try {
			memberService.join(memberForm, minterests);
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

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/edit")
	public String showEditForm(HttpSession session, Model model) {
		String mid = (String) session.getAttribute("loginMid");
		if (mid == null)
			return "redirect:/member/login";
		Member member = memberService.findByMid(mid);
		MemberForm form = new MemberForm();
		form.setMid(member.getMid());
		form.setMname(member.getMname());
		form.setMphone(member.getMphone());
		form.setMaddress(member.getMaddress());
		form.setMpw(""); // 비밀번호는 빈 값
		model.addAttribute("memberForm", form);
		return "member/edit";
	}

	@PostMapping("/edit")
	public String editMember(@ModelAttribute MemberForm form, HttpSession session) {
		String mid = (String) session.getAttribute("loginMid");
		if (mid == null)
			return "redirect:/member/login";
		memberService.updateMember(mid, form.getMname(), form.getMphone(), form.getMpw());
		session.setAttribute("mname", form.getMname());
		return "redirect:/";
	}

	@GetMapping("/myPage")
	public String reservationForm(Model model) {
		String mid = (String) session.getAttribute("loginMid");
		if (mid == null) {
			return "redirect:/member/login";
		}
		Member member = memberService.findByMid(mid);
		List<RequestAccept> requestAccepts = requestService.getRequestsByMember(member);
		List<Place> place = placeService.getPlaceByMember(member);
		model.addAttribute("member", member);
		model.addAttribute("places", place);
		model.addAttribute("requestAccepts", requestAccepts);
		model.addAttribute("today", LocalDate.now());
		return "member/myPage";
	}

	@GetMapping("/reviewWrite")
	public String showReviewForm(@RequestParam("placeId") Long placeId, @RequestParam("memberId") Long memberId,
			Model model) {
		String mid = (String) session.getAttribute("loginMid");
		if (mid == null)
			return "redirect:/member/login";
		RequestAccept requestAccept = requestService.getEntityById(placeId, memberId);

		model.addAttribute("requestAccept", requestAccept);
		model.addAttribute("replyForm", new ReplyForm());

		return "member/reviewForm";
	}

	@PostMapping("/reviewWrite")
	public String submitReview(@RequestParam("placeId") Long placeId, @ModelAttribute ReplyForm replyForm)
			throws IOException {
		String mid = (String) session.getAttribute("loginMid");
		if (mid == null) {
			return "redirect:/member/login";
		}

		replyService.writeReply(replyForm, placeId, mid);
		return "redirect:/member/myPage";
	}


}
