package bookRental;

public class Novel extends Book{
	
	private String genre;
	
	public Novel(String novelTitle, String genre) {
		super(novelTitle);
		this.genre = genre;
	}
	
	@Override
	public void bookInfo() {
		super.bookInfo();
		System.out.print("[장르] : " + genre + " ");
	}

}
