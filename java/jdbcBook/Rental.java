package jdbcBook;

public class Rental {
	
	private int rid;
	private Member member;
	private Book book;
	private String rentaldate;
	private String returndate;
	
	public Rental() {
		super();
	}
	public Rental(int rid, Member member, Book book, String rentaldate, String returndate) {
		super();
		this.rid = rid;
		this.member = member;
		this.book = book;
		this.rentaldate = rentaldate;
		this.returndate = returndate;
	}
	
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getRentaldate() {
		return rentaldate;
	}
	public void setRentaldate(String rentaldate) {
		this.rentaldate = rentaldate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	
	
	
}
