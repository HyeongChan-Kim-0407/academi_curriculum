package ykd.members.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ykd.members.dao.MemberDao;
import ykd.members.dto.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberDao memDao;
	
	public void registMember(Member member) {
		System.out.println("MemberService - registMember()");
		/* MemberDao - insert */
		memDao.insertMember(member);
	}

	public Member loginMember(String mid, String mpw) {
		System.out.println("MemberService - loginMember()");
		/* MemberDao - 회원정보 SELECT */
		Member member = memDao.selectMemberByMidAndMpw(mid, mpw);
		/* SELECT 결과를 반환 */
		return member;
	}

	public Member findByMid(String id) {
		System.out.println("MemberService - findByMid()");
		/* MemberDao - 회원정보 SELECT */
//		Member member = memDao.selectMemberByMid(id);
		/* SELECT 결과를 반환 */
		return memDao.selectMemberByMid(id);
	}

	public ArrayList<Member> findMemberList() {
		System.out.println("MemberService - findMemberList()");
		return memDao.selectMember();
	}

	public int deleteMemberByMid(String delmid) {
		System.out.println("MemberService - deleteMemberByMid()");
		return memDao.deleteMember(delmid);
	}

	public int updateMemberInfo(Member member) {
		System.out.println("MemberService - updateMemberInfo()");
		return memDao.updateMember(member);
	}
	
}











