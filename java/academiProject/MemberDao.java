package academiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {


	private Grade selectGrade(String mgrade) {

		Grade selectGrade = null;
		
		String selectSql = " SELECT * FROM GRADE WHERE GRADE = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			pstmt.setString(1, mgrade);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String grade = rs.getString("GRADE");
				int purprice = rs.getInt("PURPRICE");
				int Discount = rs.getInt("DISCOUNT");

				selectGrade = new Grade(grade, purprice, Discount);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectGrade;
	}

	public ArrayList<Member> adminSelectMember() {

		ArrayList<Member> selectList = new ArrayList<>();
		String selectSql = " SELECT * FROM MEMBER ORDER BY MGRADE ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mgrade = rs.getString("MGRADE");
				String mname = rs.getString("MNAME");
				String mstate = rs.getString("MSTATE");
				int mtotalprice = rs.getInt("MTOTALPRICE");

				Grade selectGrade = selectGrade(mgrade);

				Member selectMember = new Member(mid, mpw, mphone, maddr, selectGrade, mname, mstate, mtotalprice);

				selectList.add(selectMember);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;

	}

	public ArrayList<Member> adminSelectMemberByGrade(String grade) {

		ArrayList<Member> selectList = new ArrayList<>();
		String selectSql = " SELECT * FROM MEMBER WHERE MGRADE = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			pstmt.setString(1, grade);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mgrade = rs.getString("MGRADE");
				String mname = rs.getString("MNAME");
				String mstate = rs.getString("MSTATE");
				int mtotalprice = rs.getInt("MTOTALPRICE");

				Grade selectGrade = selectGrade(mgrade);

				Member selectMember = new Member(mid, mpw, mphone, maddr, selectGrade, mname, mstate, mtotalprice);

				selectList.add(selectMember);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;

	}

	public Member adminSelectMemberByNameOrMid(String input) {

		Member selectMem = null;
		String selectSql = " SELECT * FROM MEMBER WHERE MNAME = ? OR MID = ? ";

		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			pstmt.setString(1, input);
			pstmt.setString(2, input);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mgrade = rs.getString("MGRADE");
				String mname = rs.getString("MNAME");
				String mstate = rs.getString("MSTATE");
				int mtotalprice = rs.getInt("MTOTALPRICE");

				Grade selectGrade = selectGrade(mgrade);

				selectMem = new Member(mid, mpw, mphone, maddr, selectGrade, mname, mstate, mtotalprice);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectMem;

	}

	public Member loginGuest() {
		
		Member mem = null;
		String selectSql = " SELECT * FROM MEMBER WHERE MID = 'GUEST' ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mgrade = rs.getString("MGRADE");
				String mname = rs.getString("MNAME");
				String mstate = rs.getString("MSTATE");
				int mtotalprice = rs.getInt("MTOTALPRICE");

				Grade selectGrade = selectGrade(mgrade);

				mem = new Member(mid, mpw, mphone, maddr, selectGrade, mname, mstate, mtotalprice);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mem;
	}

	public Member loginMember(String inputId, String inputPw) {

		Member mem = null;
		String selectSql = " SELECT * FROM MEMBER WHERE MID = ? AND MPW = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mgrade = rs.getString("MGRADE");
				String mname = rs.getString("MNAME");
				String mstate = rs.getString("MSTATE");
				int mtotalprice = rs.getInt("MTOTALPRICE");

				Grade selectGrade = selectGrade(mgrade);

				mem = new Member(mid, mpw, mphone, maddr, selectGrade, mname, mstate, mtotalprice);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return mem;

	}

	public int updateMtotalPrice(int totalPrice, Member loginMember) {
		
		int updateResult = 0;
		String updateSql = " UPDATE MEMBER SET MTOTALPRICE = MTOTALPRICE + ? WHERE MID = ? ";
		
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateSql);){
			
			pstmt.setInt(1, totalPrice);
			pstmt.setString(2, loginMember.getMid());
			
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateResult;
	}

	public int insertMember(Member joinMember) {
		
		int insertResult = 0;
		String insertSql = " INSERT INTO MEMBER(MID, MPW, MPHONE, MADDR, MGRADE, MNAME, MSTATE, MTOTALPRICE) "
				+ " VALUES(?, ?, ?, ?, DEFAULT, ?, DEFAULT, DEFAULT) ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertSql);){
			
			pstmt.setString(1, joinMember.getMid());
			pstmt.setString(2, joinMember.getMpw());
			pstmt.setString(3, joinMember.getMphone());
			pstmt.setString(4, joinMember.getMaddr());
			pstmt.setString(5, joinMember.getMname());
			
			insertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertResult;
	}

	public Member selectMemberByMidAndMpw(String inputId, String inputPw) {
		
		Member selectMember = null;
		String selectSql = " SELECT * FROM MEMBER WHERE MID = ? AND MPW = ? AND MSTATE NOT IN('탈퇴') ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			pstmt.setString(1, inputId);
			pstmt.setString(2, inputPw);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String mid = rs.getString("MID");
				String mpw = rs.getString("MPW");
				String mphone = rs.getString("MPHONE");
				String maddr = rs.getString("MADDR");
				String mgrade = rs.getString("MGRADE");
				String mname = rs.getString("MNAME");
				String mstate = rs.getString("MSTATE");
				int mtotalprice = rs.getInt("MTOTALPRICE");

				Grade selectGrade = selectGrade(mgrade); 
				
				selectMember = new Member(mid, mpw, mphone, maddr, selectGrade, mname, mstate, mtotalprice);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectMember;
	}

	public int updateMstateByLoginMember(Member loginMember) {
		
		int updateResult = 0;
		
		String updateSql = " UPDATE MEMBER SET MSTATE = '탈퇴' WHERE MID = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateSql);){
			
			pstmt.setString(1, loginMember.getMid());
			
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateResult;
	}

	public int updateMemberInfo(Member loginMember) {
		
		int updateResult = 0;
		String updateSql = " UPDATE MEMBER SET MPHONE = ?, MADDR = ?, MNAME = ? WHERE MID = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateSql);){
			
			pstmt.setString(1, loginMember.getMphone());
			pstmt.setString(2, loginMember.getMaddr());
			pstmt.setString(3, loginMember.getMname());
			pstmt.setString(4, loginMember.getMid());
			
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateResult;
	}

	public void updateMgrade(Member loginMember, String newGrade) {
		String updateSql = " UPDATE MEMBER SET MGRADE = ? WHERE MID = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateSql);){
			
			pstmt.setString(1, newGrade);
			pstmt.setString(2, loginMember.getMid());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public int selectMtotalPrice(Member loginMember) {
		
		int selectTotalPrice = 0;
		String selectSql = " SELECT MTOTALPRICE FROM MEMBER WHERE MID = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			pstmt.setString(1, loginMember.getMid());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				selectTotalPrice = rs.getInt("MTOTALPRICE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return selectTotalPrice;
	}
// 06/13 회원가입 아이디 중복검사
	public boolean checkDuplicateId(String mid) {
	    String sql = "SELECT COUNT(*) FROM MEMBER WHERE MID = ?";
	    try (Connection conn = DBConnect.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);){
	        pstmt.setString(1, mid);
	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; // 중복이면 true 반환
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false; // 기본적으로 중복 아님
	}


}
