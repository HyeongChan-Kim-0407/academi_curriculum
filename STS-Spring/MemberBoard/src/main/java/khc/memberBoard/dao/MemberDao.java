package khc.memberBoard.dao;

import org.apache.ibatis.annotations.Param;

import khc.memberBoard.dto.Member;

public interface MemberDao {

	void insertMember(Member joinMember);

	Member selectMemberByMidAndMpw(@Param("mid") String mid, @Param("mpw") String mpw);

	Member selectMemberByMid(String mid);
	
	
	
}
