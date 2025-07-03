package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // try문 밖에서 선언시 conn, pstmt와 같이 해제해야하는 자원

		String keyword = "삼성";

		String sql = "SELECT * FROM PRODUCT" + " WHERE PCOMPANY LIKE '%" + keyword + "%' ";

		try {
			// 1. DB 연결
			conn = DBConnection.getConnection();
			// 2. SQL 실행준비
			pstmt = conn.prepareStatement(sql);
			// 3. SQL 실행 및 결과 확인
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getInt("PCODE") + ", ");
				System.out.print(rs.getString("PNAME") + ", ");
				System.out.print(rs.getInt("PPRICE") + ", ");
				System.out.print(rs.getString("PCOMPANY") + ", ");
				System.out.print(rs.getString("PTYPE") + ", ");
				System.out.println(rs.getInt("PSTOCK"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
