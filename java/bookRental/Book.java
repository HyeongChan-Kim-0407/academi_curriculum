package bookRental;

public class Book {
	
	private String title;
	
	public Book(String title) {
		this.title = title;
	}
	
	public void bookInfo() {
		System.out.print("[제목] : " + title + " ");
	}
	
	

}
