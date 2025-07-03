package jdbc_orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import memberOrder.OrderList;

public class OrderDao { // ORDER 테이블과의 연결만을 담당하는 클래스

	public ArrayList<Product> selectProduct() {
		String sql = "SELECT * FROM PRODUCT ORDER BY PCODE ";
		ArrayList<Product> selectList = new ArrayList<>(); // 조회된 회원 목록을 저장할 리스트

		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int pdCode = rs.getInt(1);
				String pdName = rs.getString(2);
				int pdPrice = rs.getInt(3);
				String pdCom = rs.getString(4);
				String pdType = rs.getString(5);
				int pdStock = rs.getInt(6);
				Product product = new Product(pdCode, pdName, pdPrice, pdCom, pdType, pdStock);
				selectList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

	public ArrayList<Product> selectProductByType(String selType) {
		String sql = "SELECT * FROM PRODUCT WHERE PTYPE = ? ORDER BY PCODE ";
		ArrayList<Product> selectList = new ArrayList<>(); // 조회된 회원 목록을 저장할 리스트

		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selType);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int pdCode = rs.getInt(1);
				String pdName = rs.getString(2);
				int pdPrice = rs.getInt(3);
				String pdCom = rs.getString(4);
				String pdType = rs.getString(5);
				int pdStock = rs.getInt(6);
				Product product = new Product(pdCode, pdName, pdPrice, pdCom, pdType, pdStock);
				selectList.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

//	public int insertOrdersAndUpdateProduct(Product selectPd, Member loginMember, int inputCount) {
//
//		int orderResult = 0;
//
//		String insertSql = "INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT, ODATE) "
//				+ " VALUES( (SELECT NVL(MAX(OCODE),0)+1 FROM ORDERS) , ?, ?, ?, SYSDATE)";
//		String updateSql = "UPDATE PRODUCT SET PSTOCK = PSTOCK - ? WHERE PCODE = ? ";
//
//		int updateResult = 0;
//		int insertResult = 0;
//
//		Connection conn = null;
//		PreparedStatement insertpstmt = null;
//		PreparedStatement updatepstmt = null;
//		try {
//			conn = DBConnect.getConnection();
//			insertpstmt = conn.prepareStatement(insertSql);
//			updatepstmt = conn.prepareStatement(updateSql);
//
//			insertpstmt.setInt(1, selectPd.getPcode());
//			insertpstmt.setString(2, loginMember.getMid());
//			insertpstmt.setInt(3, inputCount);
//
//			insertResult = insertpstmt.executeUpdate();
//
//			updatepstmt.setInt(1, inputCount);
//			updatepstmt.setInt(2, selectPd.getPcode());
//
//			updateResult = updatepstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		if (insertResult > 0 && updateResult > 0) {
//			orderResult = 1;
//		}
//		return orderResult;
//	}

	public ArrayList<Orders> selectOrdersByMid(Member loginMember) {
		String sql = "SELECT * FROM ORDERS WHERE OMID = ? ORDER BY OCODE ";
		ArrayList<Orders> selectList = new ArrayList<>();

		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMember.getMid());
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int ocode = rs.getInt(1);
				int oPcode = rs.getInt(2);
				String oMid = rs.getString(3);
				int oCount = rs.getInt(4);
				String oDate = rs.getString(5);
				Product selectProduct = selectProductByPcode(oPcode);
				Orders select = new Orders(ocode, selectProduct, loginMember, oCount, oDate);
				selectList.add(select);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

	public Product selectProductByPcode(int selectCode) {

		String sql = "SELECT * FROM PRODUCT WHERE PCODE = ? ORDER BY PCODE ";
		Product selectResult = new Product();

		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectCode);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int pdCode = rs.getInt(1);
				String pdName = rs.getString(2);
				int pdPrice = rs.getInt(3);
				String pdCom = rs.getString(4);
				String pdType = rs.getString(5);
				int pdStock = rs.getInt(6);
				selectResult = new Product(pdCode, pdName, pdPrice, pdCom, pdType, pdStock);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectResult;
	}

	public ArrayList<Product> selectProductByOcount() {
		String sql = " SELECT P.* FROM PRODUCT LEFT OUTER JOIN (SELECT OPCODE, COUNT(OPCODE) AS TOTALCOUNT FROM ORDERS GROUP BY OPCODE) O "
				+ " ON P.PCODE = O.OPCODE ORDER BY NVL(O.TOTALCOUNT, 0) DESC ";
		ArrayList<Product> selectList = new ArrayList<>();

		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int pdCode = rs.getInt(1);
				String pdName = rs.getString(2);
				int pdPrice = rs.getInt(3);
				String pdCom = rs.getString(4);
				String pdType = rs.getString(5);
				int pdStock = rs.getInt(6);
				Product product = new Product(pdCode, pdName, pdPrice, pdCom, pdType, pdStock);
				selectList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

	public int insertOrdersByOrder(Orders order) {
		System.out.println("Orderdao - insertAndUpdate");
		String insertSQL = "INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT, ODATE)"
				+ " VALUES( (SELECT NVL(MAX(OCODE),0)+1 FROM ORDERS) , ?, ?, ?, SYSDATE)";
		String updateSQL = "UPDATE PRODUCT SET PSTOCK = PSTOCK - ? WHERE PCODE = ? ";

		int updateResult = 0;
		int insertResult = 0;

		Connection conn = null;
		PreparedStatement insertPstmt = null;
		PreparedStatement updatePstmt = null;
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			updatePstmt = conn.prepareStatement(updateSQL);
			updatePstmt.setInt(1, order.getOcount());
			updatePstmt.setInt(2, order.getProduct().getPcode());

			updateResult = updatePstmt.executeUpdate();
			if (updateResult != 1) {
				conn.rollback();
				return 0;
			}
			insertPstmt = conn.prepareStatement(insertSQL);

			insertPstmt.setInt(1, order.getProduct().getPcode());
			insertPstmt.setString(2, order.getMember().getMid());
			insertPstmt.setInt(3, order.getOcount());

			insertResult = insertPstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException connE) {
				connE.printStackTrace();
			}
			e.printStackTrace();
		}
		return insertResult;
	}

	public ArrayList<Orders> selectOrdersByMidAndMonth(Member loginMember, String month) {

		ArrayList<Orders> selectList = new ArrayList<>();

		String sql = "SELECT * FROM ORDERS WHERE OMID = ? AND TO_CHAR(ODATE, 'MM') IN ? ORDER BY OCODE ";

		try {
			// 1. DB 연결
			Connection conn = DBConnect.getConnection();
			// 2. SQL문 실행준비
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginMember.getMid());
			pstmt.setString(2, month);
			// 3. SQL문 실행 및 결과 반환
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int ocode = rs.getInt(1);
				int oPcode = rs.getInt(2);
				String oMid = rs.getString(3);
				int oCount = rs.getInt(4);
				String oDate = rs.getString(5);
				Product selectProduct = selectProductByPcode(oPcode);
				Orders select = new Orders(ocode, selectProduct, loginMember, oCount, oDate);
				selectList.add(select);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

	public int updateProductByOCodeAndDelete(Orders cancelOrder) {

		String updateSQL = " UPDATE PRODUCT SET PSTOCK = PSTOCK + ? WHERE PCODE = ? ";
		String deleteSQL = " DELETE FROM ORDERS WHERE OCODE = ? ";

		int updateResult = 0;
		int deleteResult = 0;

		Connection conn = null;

		try {
			// 1. DB 연결
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			// 2. SQL문 실행준비
			PreparedStatement updatePstmt = conn.prepareStatement(updateSQL);
			updatePstmt.setInt(1, cancelOrder.getOcount());
			updatePstmt.setInt(2, cancelOrder.getProduct().getPcode());
			updateResult = updatePstmt.executeUpdate();
			if (updateResult != 1) {
				conn.rollback();
				return 0;
			}
			PreparedStatement deletePstmt = conn.prepareStatement(deleteSQL);
			deletePstmt.setInt(1, cancelOrder.getOcode());

			deleteResult = deletePstmt.executeUpdate();
			if (deleteResult != 1) {
				conn.rollback();
				return 0;
			}
			conn.commit();

			// 3. SQL문 실행 및 결과 반환

		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (Exception connE) {

			}
			e.printStackTrace();
			return 0;
		}

		return deleteResult;
	}

}
