package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectTest {

	public static void main(String[] args) {
		
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
		String user = "KHC"; // 오라클 데이터베이스 사용자 이름
		String password = "1234"; // 오라클 데이터베이스 비밀번호

		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 데이터베이스 연결
			connection = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM member";
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();

			// 결과 처리
			while (resultSet.next()) {
				System.out.println("Column1: " + resultSet.getString(1));
				System.out.println("Column2: " + resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
