package bookRental_1;

public class Book {

	private String title;
	
	public Book(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public String bookInfo() {
		return "[제목]"+title;
	}
	
}












