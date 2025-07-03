package ykd.members.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import ykd.members.dto.Member;

public interface MemberDao {
	
	ArrayList<Member> selectMember();

	int insertMember(Member member);

	Member selectMemberByMidAndMpw(@Param("mid") String mid, @Param("mpw") String mpw);

	Member selectMemberByMid(@Param("mid") String id);

	int deleteMember(@Param("mid") String mid);

	int updateMember(Member member);
}













