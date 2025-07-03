package bookRental_1;

public class Rental {
	private Member member; // 책을 대여한 회원
	private Book book; // 대여한 책
	
	public Member getMember() {
		return member;
	}
	public Book getBook() {
		return book;
	}
	public Rental(Member member, Book book) {
		this.member = member;
		this.book = book;
	}
	
}
