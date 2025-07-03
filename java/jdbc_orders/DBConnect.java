package jdbc_orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스 URL
	private static final String USER = "KHC"; // 오라클 데이터베이스 사용자 이름
	private static final String PASSWORD = "1234"; // 오라클 데이터베이스 비밀번호
	
	static {
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 데이터베이스 연결
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	
	
}
