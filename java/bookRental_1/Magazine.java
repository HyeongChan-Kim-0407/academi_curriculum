package bookRental_1;

public class Magazine extends Book{
	private String thema;
	
	public Magazine(String title, String thema) {
		super(title);
		this.thema = thema;
	}
	@Override
	public String bookInfo() {
		return "["+thema+"]"+getTitle();
	}
	
	
}
