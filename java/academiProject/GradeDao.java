package academiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeDao {

	public ArrayList<Grade> getAllGrades() {
		ArrayList<Grade> gradeList = new ArrayList<>();

		String sql = " SELECT * FROM GRADE WHERE GRADE NOT IN ('GUEST', '관리자') ORDER BY PURPRICE ";

		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String Grade = rs.getString("GRADE");
				int Purprice = rs.getInt("PURPRICE");
				int Discount = rs.getInt("DISCOUNT");

				Grade grade = new Grade(Grade, Purprice, Discount);
				gradeList.add(grade);
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return gradeList;
	}

	public int updateGradeBenefit(String selGrade, int purPrice, int discount) {
		String sql = "UPDATE GRADE SET PURPRICE = ?, DISCOUNT = ? WHERE GRADE = ?";
		int result = 0;

		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, purPrice);
			pstmt.setInt(2, discount);
			pstmt.setString(3, selGrade);

			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Grade selectGrade(String newGrade) {

		Grade selectGrade = null;
		String SelectSql = " SELECT * FROM GRADE WHERE GRADE = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SelectSql);) {

			pstmt.setString(1, newGrade);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String Grade = rs.getString("GRADE");
				int Purprice = rs.getInt("PURPRICE");
				int Discount = rs.getInt("DISCOUNT");

				selectGrade = new Grade(Grade, Purprice, Discount);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectGrade;
	}

}
