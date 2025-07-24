package Academy.Project.Rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Academy.Project.Rental.domain.Interests;
import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.dto.MemberDto;
import Academy.Project.Rental.dto.MemberForm;
import Academy.Project.Rental.repository.InterestRepository;
import Academy.Project.Rental.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private InterestRepository interestRepository;

	@Autowired
	private MemberRepository memberRepository;

	public MemberDto findByMidAndMpw(String mid, String mpw) {
		Member member = memberRepository.findByMidAndMpw(mid, mpw);
		if (member == null) {
			return null;
		}
		MemberDto memberDto = new MemberDto(member);

		return memberDto;
	}

	public MemberDto idCheck(String mid) {
		System.out.println("MemberService-idCheck() 호출");

		Member member = memberRepository.findByMid(mid);

		if (member == null) {
			return null;
		}

		MemberDto memberDto = new MemberDto(member);

		return memberDto;
	}

	public void join(MemberForm memberForm, String[] minterests) {
		System.out.println("MemberService-join() 호출");

		Member member = new Member(memberForm);

		memberRepository.save(member);

		for (String interest : minterests) {
			Interests interests = new Interests(member, interest);
			interestRepository.save(interests);
		}

	}

	public Member findByMid(String loginMid) {

		Member member = memberRepository.findByMid(loginMid);

		if (member == null) {
			return null;
		}

		return member;
	}

}
