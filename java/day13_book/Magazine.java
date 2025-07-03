package day13_book;

public class Magazine extends Book{
	String thema; // 잡지의 테마
	public Magazine(String title, String thema) {
		super(title);
		this.thema = thema;
	}
	@Override
	public void bookInfo() {
		super.bookInfo();
		System.out.println("테마는 "+thema+" 입니다.");
	}
	
}
