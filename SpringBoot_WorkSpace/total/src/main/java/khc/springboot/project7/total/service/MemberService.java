package khc.springboot.project7.total.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project7.total.domain.Member;
import khc.springboot.project7.total.dto.MemberDto;
import khc.springboot.project7.total.dto.MemberForm;
import khc.springboot.project7.total.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void memgerJoin(MemberForm memberForm) throws Exception {
		System.out.println("MemberService.memberJoin() 호출");
		memberForm.setMjoindate(LocalDate.now());
		memberForm.setMroute("홈페이지");
		System.out.println("memberForm: " + memberForm);
		
		Member member = new Member(memberForm);
		memberRepository.save(member);
		
	}

	public String checkId(String mid) {
		System.out.println("MemberService.checkId() 호출");
		Member member = memberRepository.findByMid(mid);
		if(member != null) {
			System.out.println("중복된 아이디가 존재합니다.");
			return "N";
		}		
		return "Y";
	}

	public MemberDto memberLogin(String mid, String mpw) {
		System.out.println("MemberService.memberLogin() 호출");
		Member member = memberRepository.findByMidAndMpw(mid, mpw);
		if(member != null) {
			MemberDto memberDto = new MemberDto(member);
			return memberDto;
		}
		
		return null;
	}

}
