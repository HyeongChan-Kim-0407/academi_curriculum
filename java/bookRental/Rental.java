package bookRental;

public class Rental {
	
	private Member member; // 책을 대여해 간 회원
	private Book book; // 회원이 대여해 간 책
	
	public Rental(Member member, Book book) {
		this.member = member;
		this.book = book;
	}

	public Member getMember() {
		return member;
	}


	public Book getBook() {
		return book;
	}

	
	
}
