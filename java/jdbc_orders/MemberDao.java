package jdbc_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {// Data Access Object : 데이터 베이스와의 연결만을 담당하는 클래스
	// MEMBER 테이블과의 연결만을 담당하는 클래스 ( 결과값을 보내주기만 함 < 데이터 판별 X )
	public Member selectMemberById(String mid) { // throws로 작업시 연결 여부 판별가능
		System.out.println("MemberDao-selectMemberById()");
		String sql = "SELECT * FROM MEMBER WHERE MID = ? ";
		Member member = null; // 조회 결과를 저장할 객체
		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String memid = rs.getString(1);
				String mempw = rs.getString(2);
				String mememail = rs.getString("MEMAIL");
				String memphone = rs.getString(4);
				String memaddr = rs.getString("MADDR");
				String memjoindate = rs.getString(6);
				member = new Member(memid, mempw, mememail, memphone, memaddr, memjoindate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return member;
	}

	public int insertMember(Member newMember) { // throws로 작업시 반환 타입으로 연결 여부 판별가능

		System.out.println("MemberDao-insertMember()");
		String sql = "INSERT INTO MEMBER(MID, MPW, MEMAIL, MPHONE, MADDR) " + " VALUES(?, ?, ?, ?, ?)";
		int insertResult = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, newMember.getMid());
			pstmt.setString(2, newMember.getMpw());
			pstmt.setString(3, newMember.getMemail());
			pstmt.setString(4, newMember.getMphone());
			pstmt.setString(5, newMember.getMaddr());

			insertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { // try 블럭 안에서 객체 생성시 try-with-resouce 혹은 try 블럭 내에서 정상 실행 후 close 진행
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			try {
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e3) {
				
			}
		}
		return insertResult;
	} 
	
	public Member selectMemberByIdAndPw(String mid, String mpw) { // throws로 작업시 연결 여부 판별가능
		System.out.println("MemberDao-selectMemberByIdAndPw()");
		String sql = "SELECT * FROM MEMBER WHERE MID = ? AND MPW = ? ";
		Member member = null; // 조회 결과를 저장할 객체
		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String memid = rs.getString(1);
				String mempw = rs.getString(2);
				String mememail = rs.getString("MEMAIL");
				String memphone = rs.getString(4);
				String memaddr = rs.getString("MADDR");
				String memjoindate = rs.getString(6);
				member = new Member(memid, mempw, mememail, memphone, memaddr, memjoindate);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return member;
	}
	
	/* 전체 회원 정보 */
	public ArrayList<Member> selectMemberList() {
		String sql = "SELECT * FROM MEMBER ";
		ArrayList<Member> selectList = new ArrayList<>(); // 조회된 회원 목록을 저장할 리스트
		
		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String memid = rs.getString(1);
				String mempw = rs.getString(2);
				String mememail = rs.getString("MEMAIL");
				String memphone = rs.getString(4);
				String memaddr = rs.getString("MADDR");
				String memjoindate = rs.getString(6);
				Member member = new Member(memid, mempw, mememail, memphone, memaddr, memjoindate);
				selectList.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return selectList;
	}
	

}
