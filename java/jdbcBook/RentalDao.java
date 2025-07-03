package jdbcBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentalDao {
	
	private Connection conn = null;
	
	public int selectRentalCountByLoginMember(Member loginMember, boolean returnDate) {
		
		int selectResult = -1;
		
		try {
			conn = DBConnect.getConnection();
			
			String selectSql = " SELECT COUNT(*) AS RENTALCOUNT FROM RENTAL WHERE RMID = ? ";
			
			if(returnDate) {
				selectSql += " AND RETURNDATE IS NULL ";
			}
			
			PreparedStatement selectPstmt = conn.prepareStatement(selectSql);
			selectPstmt.setString(1, loginMember.getMid());
			
			ResultSet rs = selectPstmt.executeQuery();
			while(rs.next()) {
				selectResult = rs.getInt("RENTALCOUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return selectResult;
	}

	public int insertRentalAndUpdateBstate(Book selBook, Member loginMember) {
		
		int insertResult = 0;
		int updateResult = 0;
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			String insertSql = " INSERT INTO RENTAL(RID, RMID, RBID, RENTALDATE, RETURNDATE) "
					+ " VALUES( (SELECT NVL(MAX(RID), 0) + 1 FROM RENTAL ), ?, ?, DEFAULT, NULL) ";
			String updateSql = " UPDATE BOOK SET BSTATE = '대여중' WHERE BID = ? ";
			
			PreparedStatement insertPstmt = conn.prepareStatement(insertSql);
			insertPstmt.setString(1, loginMember.getMid());
			insertPstmt.setInt(2, selBook.getBid());
			
			insertResult = insertPstmt.executeUpdate();
			if(insertResult != 1) {
				conn.rollback();
				return 0;
			}
			
			PreparedStatement updatePstmt = conn.prepareStatement(updateSql);
			updatePstmt.setInt(1, selBook.getBid());
			
			updateResult = updatePstmt.executeUpdate();
			
			if(updateResult != 1) {
				conn.rollback();
				return 0;
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateResult;
	}

	public ArrayList<Rental> selectRentalById(Member loginMember, boolean checkstate) {
		
		ArrayList<Rental> selectResult = new ArrayList<>();
		
		try {
			conn = DBConnect.getConnection();
			
			String selectSql = "SELECT * FROM RENTAL R INNER JOIN BOOK B ON R.RBID = B.BID WHERE RMID = ? ";
			
			if(checkstate) {
				selectSql += " AND RETURNDATE IS NULL "; 
			}
			
			PreparedStatement selectPstmt = conn.prepareStatement(selectSql);
			selectPstmt.setString(1, loginMember.getMid());
			
			ResultSet rs = selectPstmt.executeQuery();
			while(rs.next()) {
				int rid = rs.getInt("RID");
				String rmid = rs.getString("RMID");
				int rbid = rs.getInt("RBID");
				String rentaldate = rs.getString("RENTALDATE");
				String returndate = rs.getString("RETURNDATE");
				 
				int bid = rs.getInt("BID");
				String btitle = rs.getString("BTITLE");
				String bauthor = rs.getString("BAUTHOR");
				String btype = rs.getString("BTYPE");
				String bstate = rs.getString("BSTATE");
				
				if(returndate == null) {
					returndate = "대여중";
				}
				
				Book book = new Book(bid, btitle, bauthor, btype, bstate);
				Rental rental = new Rental(rid, loginMember, book, rentaldate, returndate);
				
				selectResult.add(rental);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return selectResult;
	}

	public int updateRentalAndBstate(int returnBid) {
		
		int bookResult = 0;
		int rentalResult = 0;
		
		try {
			conn = DBConnect.getConnection();
			conn.setAutoCommit(false);
			
			String bstateSql = " UPDATE BOOK SET BSTATE = '대여가능' WHERE BID = ? ";
			String rentalSql = " UPDATE RENTAL SET RETURNDATE = SYSDATE WHERE RBID = ? AND RETURNDATE IS NULL ";
			
			PreparedStatement bookPstmt = conn.prepareStatement(bstateSql);
			bookPstmt.setInt(1, returnBid);
			
			bookResult = bookPstmt.executeUpdate();
			
			if(bookResult != 1) {
				conn.rollback();
				return 0;
			}
			
			PreparedStatement rentalPstmt = conn.prepareStatement(rentalSql);
			rentalPstmt.setInt(1, returnBid);
			
			rentalResult = rentalPstmt.executeUpdate();
			
			if(rentalResult != 1) {
				conn.rollback();
				return 0;
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rentalResult;
	}

}
