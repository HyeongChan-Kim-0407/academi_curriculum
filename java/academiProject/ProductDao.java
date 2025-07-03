package academiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {
	
	public int adminInsertProduct(Product product) {
		
		int insertResult = 0;
		
		String insertSql = " INSERT INTO PRODUCT(PCODE, PNAME, PTYPE, PCATEGORY, PPRICE, PSTOCK) "
				+ " VALUES( (SELECT NVL(MAX(PCODE), 0) + 1 FROM PRODUCT), ?, ?, ?, ?, ? ) ";
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(insertSql);){	
			
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getPtype());
			pstmt.setString(3, product.getPcategory());
			pstmt.setInt(4, product.getPprice());
			pstmt.setInt(5, product.getPstock());
			
			insertResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return insertResult;
	}
	
	public Product adminSelectProductByPcode(int opcode) {
		
		Product selectPd = null;
		
		String selectSql = " SELECT * FROM PRODUCT WHERE PCODE = ? ";
		try (Connection conn = DBConnect.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectSql);){
		
			pstmt.setInt(1, opcode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int pcode = rs.getInt("PCODE");
				String pname = rs.getString("PNAME");
				String ptype = rs.getString("PTYPE");
				String pcate = rs.getString("PCATEGORY");
				int pprice = rs.getInt("PPRICE");
				int pstock = rs.getInt("PSTOCK");
				
				selectPd = new Product(pcode, pname, ptype, pcate, pprice, pstock);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectPd;
	}
	
	public int adminDeleteProduct(int pcode) {
		int result = 0;
	    String sql = "DELETE FROM PRODUCT WHERE PCODE = ?";

	    try (Connection conn = DBConnect.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    	
	    	conn.setAutoCommit(false);
	    	
	        pstmt.setInt(1, pcode); // 상품 코드 바인딩
	        result = pstmt.executeUpdate(); // 실행 결과: 영향받은 행 수 (0 또는 1)
	        
	        if(result != 0) {
	        	conn.commit();
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public ArrayList<Product> selectProduct() {
		
		ArrayList<Product> selectList = new ArrayList<>();
		
		String selectSql = " SELECT * FROM PRODUCT ";
		try (Connection conn = DBConnect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectSql);){
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int pcode = rs.getInt("PCODE");
				String pname = rs.getString("PNAME");
				String ptype = rs.getString("PTYPE");
				String pcategory = rs.getString("PCATEGORY");
				int pprice = rs.getInt("PPRICE");
				int pstock = rs.getInt("PSTOCK");
				
				Product product = new Product(pcode, pname, ptype, pcategory, pprice, pstock);
				
				selectList.add(product);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return selectList;
	}
	
	public int adminUpdateProduct(Product product) {
		
		int updateResult = 0;
		
		String updateSql = " UPDATE PRODUCT SET PNAME = ?, PTYPE = ?, PPRICE = ?, PSTOCK = ? WHERE PCODE = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateSql);){
			
			conn.setAutoCommit(false);
			
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getPtype());
			pstmt.setInt(3, product.getPprice());
			pstmt.setInt(4, product.getPstock());
			pstmt.setInt(5, product.getPcode());
			
			updateResult = pstmt.executeUpdate();
			
			if(updateResult != 0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return updateResult;
	}
	
	public ArrayList<Product> userSearchProductByType(String type){
		
		ArrayList<Product> selectList = new ArrayList<>();
		
		String selectsSql = " SELECT * FROM PRODUCT WHERE PTYPE = ? ";
		try (Connection conn = DBConnect.getConnection();			
				PreparedStatement pstmt = conn.prepareStatement(selectsSql);) {

			pstmt.setString(1, type);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pcode = rs.getInt("PCODE");
				String pname = rs.getString("PNAME");
				String ptype = rs.getString("PTYPE");
				String pcategory = rs.getString("PCATEGORY");
				int pprice = rs.getInt("PPRICE");
				int pstock = rs.getInt("PSTOCK");
				
				Product pd = new Product(pcode, pname, ptype, pcategory, pprice, pstock);
				
				selectList.add(pd);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectList;
	}

	public ArrayList<Product> searchByName(String keyword) {
		
		ArrayList<Product> selectList = new ArrayList<>();
		
		String selectsSql = " SELECT * FROM PRODUCT WHERE PNAME LIKE ? ";
		try (Connection conn = DBConnect.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectsSql);){
			
			String name = "%" + keyword + "%";
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int pcode = rs.getInt("PCODE");
				String pname = rs.getString("PNAME");
				String ptype = rs.getString("PTYPE");
				String pcategory = rs.getString("PCATEGORY");
				int pprice = rs.getInt("PPRICE");
				int pstock = rs.getInt("PSTOCK");
				
				Product pd = new Product(pcode, pname, ptype, pcategory, pprice, pstock);
				
				selectList.add(pd);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectList;
	}

	
	public int orderProduct(Orders orderProduct) {
		
		int updateResult = 0;
		
		String updateSql = " UPDATE PRODUCT SET PSTOCK = PSTOCK - ? WHERE PCODE = ? ";
		try (Connection conn = DBConnect.getConnection(); PreparedStatement updatePstmt = conn.prepareStatement(updateSql);) {
			
			conn.setAutoCommit(false);			
			updatePstmt.setInt(1, orderProduct.getOcount());
			updatePstmt.setInt(2, orderProduct.getProduct().getPcode());

			updateResult = updatePstmt.executeUpdate();
			
			if(updateResult != 0) {
				conn.commit();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateResult;
	}

	public int cancelOrder(Orders delOd) {
		
		int cancelResult = 0;
		
		String updateSql = " UPDATE PRODUCT SET PSTOCK = PSTOCK + ? WHERE PCODE = ? ";
		try (Connection conn = DBConnect.getConnection();
			PreparedStatement updatePstmt = conn.prepareStatement(updateSql);) {
			
			updatePstmt.setInt(1, delOd.getOcount());
			updatePstmt.setInt(2, delOd.getProduct().getPcode());

			cancelResult = updatePstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cancelResult;
	}
	
	
	
}
