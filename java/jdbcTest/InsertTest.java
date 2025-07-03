package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest {

	public static void main(String[] args) {
		
		Connection conn = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
		String user = "KHC"; // 오라클 데이터베이스 사용자 이름
		String password = "1234"; // 오라클 데이터베이스 비밀번호
		
		try {
			
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스 접속
			conn = DriverManager.getConnection(url, user, password);
			// SQL문 작성
			String sql = "INSERT INTO JDBCTBL(COL1, COL2, COL3) "
					+ " VALUES(?, ?, SYSDATE)";
			// SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ?값 입력
			pstmt.setInt(1, 1000);
			pstmt.setString(2, "문자");
			int result = pstmt.executeUpdate();
			System.out.println(result);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
