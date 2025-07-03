package jdbcBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {
	
	private Connection conn = null;
	
	public Member selectMemberByIdAndPw(String inputId, String inputPw) {
		
		String selectSql = "SELECT * FROM MEMBER WHERE MID = ? AND MPW = ? ";
		Member selectMember = null;
		try {
			conn = DBConnect.getConnection();
			PreparedStatement selectPstmt = conn.prepareStatement(selectSql);
			
			selectPstmt.setString(1, inputId);
			selectPstmt.setString(2, inputPw);
			
			ResultSet rs = selectPstmt.executeQuery();
			while(rs.next()) {
				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String memail = rs.getString("MEMAIL");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mjoindate = rs.getString("MJOINDATE");
				
				selectMember = new Member(mid, mpw, memail, mphone, maddr, mjoindate);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectMember;
	}

	public int insertJoinMember(Member joinMember) {
		
		int insertResult = 0;
		
		try {
			conn = DBConnect.getConnection();
			
			String insertSql = " INSERT INTO MEMBER VALUES( ?, ?, ?, ?, ?, DEFAULT ) ";
			
			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, joinMember.getMid());
			pstmt.setString(2, joinMember.getMpw());
			pstmt.setString(3, joinMember.getMemail());
			pstmt.setString(4, joinMember.getMphone());
			pstmt.setString(5, joinMember.getMaddr());
			
			insertResult = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertResult;
	}

	public ArrayList<Member> selectMemberWithRental() {
		
		ArrayList<Member> selectList = new ArrayList<>(); 
		
		try {
			conn = DBConnect.getConnection();
			
			String selectSql = " SELECT M.*, 대여권수, 현재대여권수 "
					+ " FROM MEMBER M "
					+ "    LEFT OUTER JOIN (SELECT M.MID, COUNT(RENTALDATE) AS 대여권수, (COUNT(RENTALDATE) - COUNT(RETURNDATE)) AS 현재대여권수 "
					+ "	   FROM MEMBER M LEFT OUTER JOIN RENTAL R ON M.MID = R.RMID GROUP BY M.MID) R "
					+ "    ON M.MID = R.MID ";
			
			PreparedStatement pstmt = conn.prepareStatement(selectSql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String memail = rs.getString("MEMAIL");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mjoindate = rs.getString("MJOINDATE");
				int totalRental = rs.getInt("대여권수");
				int rental = rs.getInt("현재대여권수");
				
				Member selectMember = new Member(mid, mpw, memail, mphone, maddr, mjoindate, totalRental, rental);
				
				selectList.add(selectMember);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectList;
	}
	
	
	
	
	
	
}
