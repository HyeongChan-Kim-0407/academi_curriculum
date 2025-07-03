package day13_book;

public class Novel extends Book{
	String genre; // 소설의 장르
	public Novel(String title, String genre) {
//		super();
//		this.title = title;
//		this.genre = genre;
		super(title);
		this.genre = genre;
	}
	@Override
	public void bookInfo() {
		super.bookInfo();
		System.out.println("책 장르는 "+genre+" 입니다.");
	}
	
	
	
}











