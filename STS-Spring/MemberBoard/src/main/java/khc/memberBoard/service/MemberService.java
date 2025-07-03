package khc.memberBoard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.memberBoard.dao.MemberDao;
import khc.memberBoard.dto.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	/* 회원 가입 기능 */
	
	public void registMember(Member joinMember) {
		/* joinMember에 저장된 가입 정보를 member 테이블에 insert */
		memberDao.insertMember(joinMember);
		
	}

	public Member findMember(String mid, String mpw) {
		
		Member member = memberDao.selectMemberByMidAndMpw(mid,mpw);
		
		return member;
	}

	public String idCheck(String userid) {
		
		System.out.println("MemberService-idCheck() 호출");
		Member member = memberDao.selectMemberByMid(userid);
		if(member == null) { // 회원이 조회되지 않는 경우 ( 사용가능 ) 
			return "y";
		} else {
			return "n";
		}
	}
	
	/* 회원 조회 기능 */
	
}
