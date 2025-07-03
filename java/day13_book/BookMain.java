package day13_book;

public class BookMain {

	public static void main(String[] args) {
		Object obj = new Book("제목1");
		
		Book book = new Book("제목1");
		// 책의 정보를 출력하는 bookInfo() 메소드 호출
		book.bookInfo();
		// "책의 제목은 제목1 입니다."
		
		Book book1 = new Novel("셜록홈즈","추리");
		book1.bookInfo(); 
		// "책의 제목은 셜록홈즈 입니다."
		// "책의 장르는 추리 입니다."
		Book book2 = new Magazine("GQ","패션");
		book2.bookInfo();
		Book book3 = new Comic("드래곤볼", "컬러");
		System.out.println();
		System.out.println( book1 instanceof Novel  ); 
		System.out.println( book1 instanceof Magazine  );
		System.out.println( book1 instanceof Comic  );
		
		Book bookObj;
		int select = 2;
		switch(select) {
		case 1:
			bookObj = new Novel("셜록홈즈","추리");
			break;
		case 2:
			bookObj = new Magazine("GQ","패션");
			break;
		default:
			bookObj = new Comic("드래곤볼", "컬러");
		}
		
		if( bookObj instanceof Novel ) {
			System.out.println("bookObj는 Novel입니다.");
			Novel novel = (Novel)book1;
			System.out.println( novel.genre );
		} else if( book2 instanceof Magazine mgz) {
			System.out.println("bookObj는 Magazine입니다.");
			System.out.println( mgz.thema );
		} else if (book2 instanceof Comic comic) {
			System.out.println("bookObj는 Comic입니다.");
			System.out.println( comic.printType );
		}
	}

}




