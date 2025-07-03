package member.khc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.khc.dao.MemberDao;
import member.khc.dto.Member;

@Service 
//Service 어노테이션
public class MemberService {
	
	@Autowired
	private MemberDao mdao;
	
	public ArrayList<Member> findMemberList(){
		System.out.println("MemberService-findMemberList()");
		return mdao.selectMember();
	}
	
	public int registMember(Member member) {
		System.out.println("MemberService-registMember()");
		
		/* MemberDao - insert */
		
		int result = mdao.insertMember(member);
		System.out.println(result);
		return 0;
	}


	public Member loginMember(String mid, String mpw) {
		System.out.println("MemberService-loginMember");
		Member member = mdao.selectMemberByMidAndMpw(mid, mpw);
		
		return member;
	}


	public Member findByMid(String loginId) {
		System.out.println("MemberService-findMember");

//		Member member = mdao.selectMemberByMid(loginId);
		
		return mdao.selectMemberByMid(loginId);
	}
	
	public int deleteByMid(String memberId) {
		System.out.println("MemberService - deleteMember");
		return mdao.deleteMember(memberId);
	}

	public int updateMemberInfo(Member modifyMember) {
		System.out.println("MemberService - updateMember");
		return mdao.updateMember(modifyMember);
	}
	
}
