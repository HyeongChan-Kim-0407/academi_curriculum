package bookRental_1;

import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {

	static Scanner scan = new Scanner(System.in);
	private ArrayList<Member> memberList; // 회원 목록
	private ArrayList<Book> bookList;	  // 책 목록
	private ArrayList<Rental> rentalList; // 대여 정보 목록
	private Member loginMember;
	public Member getLoginMember() {
		return loginMember;
	}
	public RentalService() {
		this.memberList = new ArrayList<>();
		this.bookList = new ArrayList<>();
		this.rentalList = new ArrayList<>();
	}
	public void start() {
		// 테스트 회원 등록
		memberList.add(  new Member("test1","1111") );
		memberList.add(  new Member("test2","2222") );
		System.out.println("회원 "+memberList.size()+"명 등록되었습니다.");
		// 책 등록
		bookList.add( new Novel( "소년이 온다","역사" ) );
		bookList.add( new Magazine( "포브스코리아","경제" ) );
		bookList.add( new Novel( "스토너","인생" ) );
		bookList.add( new Magazine( "Design 디자인","예술" ) );
		bookList.add( new Novel( "달까지 가자","청춘" ) );
		bookList.add( new Novel( "살인자의 쇼핑몰","스릴러" ) );
		bookList.add( new Magazine( "씨네21","영화" ) );
		System.out.println("도서 "+bookList.size()+"권 등록되었습니다.");
		loginMember = memberList.get(0);
	}
	
	// 회원가입 기능
	
	// 로그인 기능
	
	// 로그아웃 기능
	
	// [3]도서대출 기능
	public void rentBook() {
		// 책의 종류 선택
		System.out.println("[도서대출기능]");
		System.out.println("[1]소설 [2]잡지");
		System.out.print("선택>>");
		int selectType = scan.nextInt();
		// 전체 도서 목록에서 선택한 종류의 도서 목록으로 분류 
		ArrayList<Book> fillterList = new ArrayList<>();
		if( selectType == 1) { // 소설을 선택한 경우
			for(int idx = 0; idx < bookList.size(); idx++) {
				if( bookList.get(idx) instanceof Novel  ) {
					fillterList.add( bookList.get(idx)  );
				}
			}
		} else if( selectType == 2 ) { // 잡지를 선택한 경우
			for(int idx = 0; idx < bookList.size(); idx++) {
				if( bookList.get(idx) instanceof Magazine  ) {
					fillterList.add( bookList.get(idx)  );
				}
			}
		} else { // 다른 번호를 누른 경우
			fillterList = bookList;
		}
		// 도서 목록 출력
		for(int idx = 0; idx < fillterList.size(); idx++) {
			Book book = fillterList.get(idx); // 대여중인지 판별할 책
			boolean isRental = isRental(book); //  true:대여중 , false:대여가능
			// 조건식 ? true일때 : false일때
			String bookStatus = isRental ? "(대여중)" : "(대여가능)";
			System.out.println("[" + idx + "]"+ book.bookInfo() + bookStatus  );
		}
		// 대여할 도서 선택
		System.out.print("대여할 번호입력>>");
		int inputIdx = scan.nextInt();
		// 선택한 도서의 대여 처리
		Book rentBook = fillterList.get(inputIdx);
		Rental rent = new Rental( loginMember, rentBook  );
		rentalList.add(rent);
		System.out.println("대여 처리 되었습니다.");
	}
	
	// Book의 대여상태 판별  
	private boolean isRental(Book book) {
		for(int idx = 0; idx < rentalList.size(); idx++) {
			Rental rental = rentalList.get(idx);
			if( book.equals( rental.getBook() ) ) {
				return true;
			}
		}
		return false;
	}
	
	
	
}

























