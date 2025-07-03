package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
		String user = "KHC"; // 오라클 데이터베이스 사용자 이름
		String password = "1234"; // 오라클 데이터베이스 비밀번호
		
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
