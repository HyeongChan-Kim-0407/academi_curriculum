package jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest2 {

	public static void main(String[] args) {
		// JDBCTBL에 사용자 입력값 저장
		Scanner scan = new Scanner(System.in);
		System.out.print("COL1 컬럼 : ");
		int col1 = scan.nextInt();
		System.out.print("COL2 컬럼 : ");
		String col2 = scan.next();
		System.out.print("COL3 컬럼(YYYY-MM-DD) : ");
		String col3 = scan.next();

		
		try {
			
			Connection conn = DBConnection.getConnection();
			if(conn == null) {
				System.out.println("DB접속 실패");
				return;
			}

			/* 2. SQL문 작성 */
			String sql = "INSERT INTO JDBCTBL(COL1, COL2, COL3) " + " VALUES(?, ?, ?)";

			/* 3. 실행 및 결과 확인 */
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, col1);
			pstmt.setString(2, col2);
			pstmt.setString(3, col3);
			int insertResult = pstmt.executeUpdate();
			System.out.println("결과 : " + insertResult);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
