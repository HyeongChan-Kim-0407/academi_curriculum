package jdbcBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDao {

	private Connection conn = null;

	public int insertBook(Book book) {

		String insertSql = " INSERT INTO BOOK(BID, BTITLE, BAUTHOR, BTYPE) "
				+ " VALUES( (SELECT NVL(MAX(BID),0) + 1 FROM BOOK ), ?, ?, ? ) ";
		int insertResult = 0;

		// 1. DB연결
		try {

			conn = DBConnect.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(insertSql);
			pstmt.setString(1, book.getBtitle());
			pstmt.setString(2, book.getBauthor());
			pstmt.setString(3, book.getBtype());

			insertResult = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return insertResult;
	}

	public ArrayList<Book> selectBook(boolean checkState) {
		ArrayList<Book> selectList = new ArrayList<>();

		String selectSql = " SELECT * FROM BOOK ";

		if (checkState) {
			selectSql += " WHERE BSTATE = '대여가능' ";
		}

		try {
			conn = DBConnect.getConnection();
			PreparedStatement selectPstmt = conn.prepareStatement(selectSql);

			ResultSet rs = selectPstmt.executeQuery();
			while (rs.next()) {
				int bid = rs.getInt("BID");
				String btitle = rs.getString("BTITLE");
				String bauthor = rs.getString("BAUTHOR");
				String btype = rs.getString("BTYPE");
				String bstate = rs.getString("BSTATE");

				Book book = new Book(bid, btitle, bauthor, btype, bstate);
				selectList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return selectList;
	}

	public ArrayList<Book> selectBookByTitle(String findTitle, boolean checkState) {

		ArrayList<Book> selectList = new ArrayList<>();
		String selectSql = " SELECT * FROM BOOK WHERE BTITLE LIKE '%'|| ? || '%' ";
//		String selectSql = " SELECT * FROM BOOK WHERE BTITLE LIKE ? ";

		if (checkState) {
			selectSql += " AND BSTATE = '대여가능' ";
		}

		try {
			conn = DBConnect.getConnection();
			PreparedStatement selectPstmt = conn.prepareStatement(selectSql);
			selectPstmt.setString(1, findTitle);
//			selectPstmt.setString(1, "%" + findTitle + "%");

			ResultSet rs = selectPstmt.executeQuery();
			while (rs.next()) {
				int bid = rs.getInt("BID");
				String btitle = rs.getString("BTITLE");
				String bauthor = rs.getString("BAUTHOR");
				String btype = rs.getString("BTYPE");
				String bstate = rs.getString("BSTATE");

				Book book = new Book(bid, btitle, bauthor, btype, bstate);
				selectList.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

	public ArrayList<Book> selectBookByType(String findType, boolean checkState) {

		ArrayList<Book> selectList = new ArrayList<>();
		String selectSql = " SELECT * FROM BOOK WHERE BTYPE IN ? ";

		if (checkState) {
			selectSql += " AND BSTATE = '대여가능' ";
		}

		try {
			conn = DBConnect.getConnection();
			PreparedStatement selectPstmt = conn.prepareStatement(selectSql);

			selectPstmt.setString(1, findType);

			ResultSet rs = selectPstmt.executeQuery();
			while (rs.next()) {
				int bid = rs.getInt("BID");
				String btitle = rs.getString("BTITLE");
				String bauthor = rs.getString("BAUTHOR");
				String btype = rs.getString("BTYPE");
				String bstate = rs.getString("BSTATE");

				Book book = new Book(bid, btitle, bauthor, btype, bstate);
				selectList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selectList;
	}

	public ArrayList<Book> selectBookByCount(boolean checkState) {

		ArrayList<Book> selectList = new ArrayList<>();

		String selectSql = " SELECT B.*, NVL(대여횟수, 0) AS 대여횟수 FROM BOOK B LEFT OUTER JOIN "
				+ " (SELECT RBID, COUNT(RBID) AS 대여횟수 FROM RENTAL GROUP BY RBID) R"
				+ " ON B.BID = R.RBID ORDER BY 대여횟수 DESC ";

		if (checkState) {
			selectSql = " SELECT B.*, NVL(대여횟수, 0) AS 대여횟수 FROM BOOK B LEFT OUTER JOIN "
					+ " (SELECT RBID, COUNT(RBID) AS 대여횟수 FROM RENTAL GROUP BY RBID) R "
					+ " ON B.BID = R.RBID WHERE BSTATE = '대여가능' ORDER BY 대여횟수 DESC " ;
		}

		try {
			conn = DBConnect.getConnection();
			PreparedStatement selectPstmt = conn.prepareStatement(selectSql);

			ResultSet rs = selectPstmt.executeQuery();
			while (rs.next()) {
				int bid = rs.getInt("BID");
				String btitle = rs.getString("BTITLE");
				String bauthor = rs.getString("BAUTHOR");
				String btype = rs.getString("BTYPE");
				String bstate = rs.getString("BSTATE");

				Book book = new Book(bid, btitle, bauthor, btype, bstate);
				selectList.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return selectList;
	}

}
