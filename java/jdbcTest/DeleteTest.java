package jdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteTest {

	public static void main(String[] args) {

		String sql = "DELETE FROM JDBCTBL"; // JDBCTBL 테이블에 있는 모든 레코드를 삭제
		
		try (Connection conn = DBConnection.getConnection(); // try with resource : try문이 끝났을 때 사용된 리소스를 자동으로 close
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			int result = pstmt.executeUpdate();
			System.out.println("result : " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
