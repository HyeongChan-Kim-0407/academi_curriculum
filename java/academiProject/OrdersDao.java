package academiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersDao {

	private ProductDao pdao = new ProductDao();
	private MemberDao mdao = new MemberDao();

	public int selectOcode() {

		int selResult = 0;
		String sql = " SELECT NVL(MAX(OCODE),0) + 1 FROM ORDERS ";

		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);){
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				selResult = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selResult;
	}
	
	public int insertOrderByRealTime(int ocode, Orders od, Member loginMember, String PurWay) {

		int insertResult = 0;
		String orderSql = " INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT, ORDERDATE, PURDATE, PURWAY, ORDERSTATE) "
				+ " VALUES( ?, ?, ?, ?, DEFAULT, SYSDATE, ?, '결제완료' ) ";
		
		try (Connection conn = DBConnect.getConnection(); PreparedStatement orderPstmt = conn.prepareStatement(orderSql);){

			orderPstmt.setInt(1, ocode);
			orderPstmt.setInt(2, od.getProduct().getPcode());
			orderPstmt.setString(3, loginMember.getMid());
			orderPstmt.setInt(4, od.getOcount());
			orderPstmt.setString(5, PurWay);
			
			insertResult = orderPstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertResult;

	}
	
	public int insertOrderByNonRT(int ocode, Orders od, Member loginMember, String PurWay) {

		int insertResult = 0;
		String orderSql = " INSERT INTO ORDERS(OCODE, OPCODE, OMID, OCOUNT, ORDERDATE, PURDATE, PURWAY, ORDERSTATE) "
				+ " VALUES( ?, ?, ?, ?, DEFAULT, null, ?, DEFAULT ) ";
		
		try (Connection conn = DBConnect.getConnection(); PreparedStatement orderPstmt = conn.prepareStatement(orderSql);){

			orderPstmt.setInt(1, ocode);
			orderPstmt.setInt(2, od.getProduct().getPcode());
			orderPstmt.setString(3, loginMember.getMid());
			orderPstmt.setInt(4, od.getOcount());
			orderPstmt.setString(5, PurWay);
			
			insertResult = orderPstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertResult;

	}

	public ArrayList<Orders> SelectOrdersByNotFixed() {

		ArrayList<Orders> selectList = new ArrayList<>();
		String selectSql = " SELECT * FROM ORDERS WHERE ORDERSTATE IN ('결제필요','결제완료') ";

		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int ocode = rs.getInt("OCODE");
				int opcode = rs.getInt("OPCODE");
				String omid = rs.getString("OMID");
				int ocount = rs.getInt("OCOUNT");
				String orderdate = rs.getString("ORDERDATE");
				String purdate = rs.getString("PURDATE");
				String purway = rs.getString("PURWAY");
				String orderstate = rs.getString("ORDERSTATE");
				Product selectPd = pdao.adminSelectProductByPcode(opcode);
				Member selectMem = mdao.adminSelectMemberByNameOrMid(omid);

				Orders od = new Orders(ocode, selectPd, selectMem, ocount, orderdate, purdate, purway, orderstate);

				selectList.add(od);

				selectList.add(od);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

	public ArrayList<Orders> SelectOrdersByFixed() {

		ArrayList<Orders> selectList = new ArrayList<>();
		String selectSql = " SELECT * FROM ORDERS WHERE ORDERSTATE IN('주문확정') ";

		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int ocode = rs.getInt("OCODE");
				int opcode = rs.getInt("OPCODE");
				String omid = rs.getString("OMID");
				int ocount = rs.getInt("OCOUNT");
				String orderdate = rs.getString("ORDERDATE");
				String purdate = rs.getString("PURDATE");
				String purway = rs.getString("PURWAY");
				String orderstate = rs.getString("ORDERSTATE");
				Product selectPd = pdao.adminSelectProductByPcode(opcode);
				Member selectMem = mdao.adminSelectMemberByNameOrMid(omid);

				Orders od = new Orders(ocode, selectPd, selectMem, ocount, orderdate, purdate, purway, orderstate);

				selectList.add(od);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}
	
	public ArrayList<Orders> selectOrdersByOcode(int inputOcode) {
		
		ArrayList<Orders> selectList = new ArrayList<>();
		
		String selectSql = " SELECT * FROM ORDERS WHERE OCODE = ? AND ORDERSTATE NOT IN('주문취소') ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement sPstmt = conn.prepareStatement(selectSql);){
			
			sPstmt.setInt(1, inputOcode);
			
			ResultSet rs = sPstmt.executeQuery();
			
			while(rs.next()) {
				int ocode = rs.getInt("OCODE");
				int opcode = rs.getInt("OPCODE");
				String omid = rs.getString("OMID");
				int ocount = rs.getInt("OCOUNT");
				String orderdate = rs.getString("ORDERDATE");
				String purdate = rs.getString("PURDATE");
				String purway = rs.getString("PURWAY");
				String orderstate = rs.getString("ORDERSTATE");
				Product selectPd = pdao.adminSelectProductByPcode(opcode);
				Member selectMem = mdao.adminSelectMemberByNameOrMid(omid);

				Orders od = new Orders(ocode, selectPd, selectMem, ocount, orderdate, purdate, purway, orderstate);

				selectList.add(od);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectList;
	}
	
	public int updateOrderState(ArrayList<Orders> selOd) {

		int updateResult = 0;

		String updateSql = " UPDATE ORDERS SET ORDERSTATE = '결제완료' WHERE OCODE = ? AND OPCODE = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateSql);){
			
			for (Orders od : selOd) {
			pstmt.setInt(1, od.getOcode());
			pstmt.setInt(2, od.getProduct().getPcode());

			updateResult += pstmt.executeUpdate();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return updateResult;
	}

	public int updateOrderStateAndUpdateProduct(ArrayList<Orders> selOd) {

		int ordersResult = 0;
		int productResult = 0;

		String ordersSql = " UPDATE ORDERS SET ORDERSTATE = '주문취소' WHERE OCODE = ? AND OPCODE = ? ";
		String productSql = " UPDATE PRODUCT SET PSTOCK = PSTOCK + ? WHERE PCODE = ? ";
		
		try (Connection conn = DBConnect.getConnection(); PreparedStatement oPstmt = conn.prepareStatement(ordersSql);
				PreparedStatement pPstmt = conn.prepareStatement(productSql);){
			conn.setAutoCommit(false);
			
			for (Orders od : selOd) {
				
				oPstmt.setInt(1, od.getOcode());
				oPstmt.setInt(2, od.getProduct().getPcode());
				
				ordersResult = oPstmt.executeUpdate();
				
				if(ordersResult <= 0) {
					return 0;
				}
				
				pPstmt.setInt(1, od.getOcount());
				pPstmt.setInt(2, od.getProduct().getPcode());
				
				productResult++;
				
			}
			
			if(productResult != 0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return productResult;
	}

	public ArrayList<Orders> selectOrderByOMid(Member loginMember) {
		
		ArrayList<Orders> selectList = new ArrayList<>();
		
		String selectSql = " SELECT * FROM ORDERS WHERE OMID = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			pstmt.setString(1, loginMember.getMid());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int ocode = rs.getInt("OCODE");
				int opcode = rs.getInt("OPCODE");
				String omid = rs.getString("OMID");
				int ocount = rs.getInt("OCOUNT");
				String orderdate = rs.getString("ORDERDATE");
				String purdate = rs.getString("PURDATE");
				String purway = rs.getString("PURWAY");
				String orderstate = rs.getString("ORDERSTATE");
				Product selectPd = pdao.adminSelectProductByPcode(opcode);
				Member selectMem = mdao.adminSelectMemberByNameOrMid(omid);
				
				if(purdate == null) {
					purdate = " ";
				}

				Orders od = new Orders(ocode, selectPd, selectMem, ocount, orderdate, purdate, purway, orderstate);

				selectList.add(od);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectList;
	}

	public void updateOrderStateByDate() {
		
		String cancelSql = " UPDATE ORDERS SET ORDERSTATE = '주문취소' WHERE SYSDATE >= ORDERDATE + 8 AND ORDERSTATE IN('결제필요') ";
		
		String correctSql = " UPDATE ORDERS SET ORDERSTATE = '주문확정' WHERE SYSDATE >= PURDATE + 8 AND ORDERSTATE IN('결제완료') ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement canpstmt = conn.prepareStatement(cancelSql);
				PreparedStatement corPstmt = conn.prepareStatement(correctSql);){
			
			canpstmt.executeUpdate();
			
			corPstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	

	

}
