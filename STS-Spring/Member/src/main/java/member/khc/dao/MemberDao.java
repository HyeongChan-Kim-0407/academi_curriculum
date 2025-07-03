package member.khc.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import member.khc.dto.Member;

public interface MemberDao {
	
	ArrayList<Member> selectMember();

	int insertMember(Member member);
	
	Member selectMemberByMidAndMpw(@Param("mid") String mid, @Param("mpw") String mpw);
	
//	사용되는 파라미터가 하나일 때는 @Param 생략 가능
	Member selectMemberByMid(@Param("mid") String loginId);
	
	int deleteMember(@Param("mid") String memberId);

	int updateMember(Member modifyMember);
	
}
