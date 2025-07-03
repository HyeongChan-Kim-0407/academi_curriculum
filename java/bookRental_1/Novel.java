package bookRental_1;

public class Novel extends Book{

	private String genre;
	
	public Novel(String title, String genre) {
		super(title);
		this.genre = genre;
	}
	//bookInfo() 재정의
	@Override
	public String bookInfo() {
		return "["+genre+"]"+getTitle();
	}
	
}







