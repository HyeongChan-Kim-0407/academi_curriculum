package day13_book;

public class Book {
	String title;	// 제목
	public Book(String title) {
		this.title = title;
	}
	public void bookInfo() {
		System.out.println("책 제목은 " + title + " 입니다");
	}
}
