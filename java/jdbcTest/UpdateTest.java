package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.print("레코드 선별할 COL1의 값 : "); // where
		int col1 = scan.nextInt();
		System.out.print("새 COL2의 값 : "); // set
		String col2 = scan.next();

		String sql = "UPDATE JDBCTBL "
				+ " SET COL2 = ? " 		// 입력받은 col2
				+ " WHERE COL1 = ? "; 	// 입력받은 col1
		// DB 접속
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. DB 연결
			conn = DBConnection.getConnection();
			conn.setAutoCommit(true);
			pstmt = conn.prepareStatement(sql);
			// 2. ? 값 입력
			pstmt.setString(1, col2);
			pstmt.setInt(2, col1);
			// 3. sql문 실행 및 결과 확인
			int result = pstmt.executeUpdate();
			System.out.println("result : " + result); // 업데이트된 행의 갯수 출력
//			if (result == 1) {
//				conn.commit(); // commit
//			} else {
//				conn.rollback(); // rollback
//			}

		} catch (SQLException e) {
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
