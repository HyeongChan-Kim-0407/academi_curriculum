package khc.springboot.project2.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project2.orders.domain.Member;
import khc.springboot.project2.orders.dto.MemberDto;
import khc.springboot.project2.orders.dto.MemberForm;
import khc.springboot.project2.orders.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public void joinMember(MemberForm memberForm) {
		System.out.println("MemberService - joinMember() 호출");
		Member member = new Member(memberForm);
		
		memberRepository.save(member);
		
	}

	public MemberDto findByMidAndMpw(String mid, String mpw) {
		System.out.println("MemberService - findByMidAndMpw() 호출");
		
		Member member = memberRepository.findByMidAndMpw(mid, mpw);
		
		if(member == null) {
			return null;
		}
		
		MemberDto memberDto = new MemberDto(member);
		
		return memberDto;
	}

	public MemberDto findByMid(String mid) {
		System.out.println("MemberService - findByMid() 호출");
		
		Member member = memberRepository.findByMid(mid);
		
		if(member == null) {
			return null; // 회원이 존재하지 않으면 null 반환
		}
		MemberDto memberDto = new MemberDto(member);
		
		return memberDto;
	}

}
