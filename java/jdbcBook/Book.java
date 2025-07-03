package jdbcBook;

import java.sql.BatchUpdateException;

public class Book {
	
	private int bid;			//도서번호
	private String btitle;		//도서명
	private String bauthor;		//저자
	private String btype;		//도서종류
	private String bstate;		//대여가능여부
	
	public Book(int bid, String btitle, String bauthor, String btype, String bstate) {
		this.bid = bid;
		this.btitle = btitle;
		this.bauthor = bauthor;
		this.btype = btype;
		this.bstate = bstate;
	}
	
	public Book(String title, String author, String type) {
		btitle = title;
		bauthor = author;
		btype = type;
	}

	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getBstate() {
		return bstate;
	}
	public void setBstate(String bstate) {
		this.bstate = bstate;
	}
	
	
	
}
