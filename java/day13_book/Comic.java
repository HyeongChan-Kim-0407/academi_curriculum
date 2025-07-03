package day13_book;

public class Comic extends Book{
	String printType;
	
	public Comic(String title, String printType) {
		super(title);
		this.printType = printType;
	}
}
