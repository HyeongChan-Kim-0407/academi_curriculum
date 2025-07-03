package bookRental;

public class Magazine extends Book{
	
	private String thema;
	
	public Magazine(String magazineTitle, String thema) {
		super(magazineTitle);
		this.thema = thema;
	}
	
	@Override
	public void bookInfo() {
		super.bookInfo();
		System.out.print("[테마] : " + thema + " ");
	}

}
