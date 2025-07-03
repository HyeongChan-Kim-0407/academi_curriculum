package bookRental;

import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {

	static Scanner scan = new Scanner(System.in);

	private ArrayList<Member> memberList; // 회원목록
	private ArrayList<Book> bookList; // 도서목록
	private ArrayList<Rental> rentalList; // 대여목록
	private Member loginMember;

	public Member getLoginMember() {
		return loginMember;
	}

	public RentalService() { // 객체 생성시 ArrayList 필드 초기화
		this.memberList = new ArrayList<>();
		this.bookList = new ArrayList<>();
		this.rentalList = new ArrayList<>();
	}

	public void start() {
		loginMember = null;
		// 테스트용 회원 등록
		memberList.add(new Member("test1", "1111"));
		memberList.add(new Member("test2", "2222"));
		// 테스트용 도서 등록
		// 소설
		bookList.add(new Novel("셜록홈즈", "추리"));
		bookList.add(new Novel("해리포터", "판타지"));
		bookList.add(new Novel("삼국지", "역사"));
		bookList.add(new Novel("살인자의 기억법", "스릴러"));
		bookList.add(new Novel("룬의 아이들", "판타지"));
		// 잡지
		bookList.add(new Magazine("보그", "패션"));
		bookList.add(new Magazine("맥심", "남성"));
		bookList.add(new Magazine("마리끌레르", "여성"));
		bookList.add(new Magazine("뉴턴", "과학"));
		bookList.add(new Magazine("포브스", "경제"));
		
		System.out.println("회원 " + memberList.size() + "명 등록되었습니다.");
		System.out.println("도서 " + bookList.size() + "권 등록되었습니다.");
		
	}

	// 회원가입
	public void register() {

		System.out.print("가입하실 아이디를 입력해주십시오 : ");
		String inputId = scan.next();
		System.out.print("가입하실 비밀번호를 입력해주십시오 : ");
		String inputPw = scan.next();

		Member check = new Member(inputId, inputPw);
		for (int idx = 0; idx < memberList.size(); idx++) {
			if (check.getmId().equals(memberList.get(idx).getmId())) {
				System.out.println("입력하신 아이디는 사용 불가합니다.");
				return;
			} else {
				memberList.add(check);
				System.out.println("회원가입이 완료되었습니다.");
			}
		}

	}

	// 로그인
	public void login() {

		System.out.print("아이디를 입력해주십시오. : ");
		String inputId = scan.next();
		System.out.print("비밀번호를 입력해주십시오. : ");
		String inputPw = scan.next();

		Member check = new Member(inputId, inputPw);
		for (int idx = 0; idx < memberList.size(); idx++) {
			if (check.getmId().equals(memberList.get(idx).getmId())
					&& check.getmPw().equals(memberList.get(idx).getmPw())) {
				loginMember = check;
				break;
			}
		}
		if (loginMember == null) {
			System.out.println("아이디 혹은 비밀번호를 확인해주십시오.");
		} else {
			System.out.println("로그인 성공");
			System.out.println("환영합니다 " + loginMember.getmId() + "님");
		}

	}

	// 로그아웃
	public void logout() {

		loginMember = null;

	}

	// [3] 도서대출
	public void rentalBook() {
		if (loginMember == null) {
			System.out.println("로그인을 선행해주십시오.");
			return;
		}
		System.out.println("[도서대출]");
		// 도서 종류 선택
		System.out.print("대여할 도서 분류를 선택해주십시오 [1]소설 [2]잡지 [3]모두 : ");
		int selectType = scan.nextInt();
		ArrayList<Book> filterList = new ArrayList<>();
//		ArrayList<Book> magList = new ArrayList();
		// 도서 목록 분류
		if (selectType == 1) { // 소설 도서 분류
			for (int idx = 0; idx < bookList.size(); idx++) {
				if (bookList.get(idx) instanceof Novel) {
					Book novel = bookList.get(idx);
					filterList.add(novel);
//					filterList.add(bookList.get(idx));

				}
			}
		} else if (selectType == 2)

		{ // 잡지 도서 분류
			for (int idx = 0; idx < bookList.size(); idx++) {
				if (bookList.get(idx) instanceof Magazine) {
					Book mag = bookList.get(idx);
					filterList.add(mag);
//					filterList.add(bookList.get(idx));
				}
			}

		} else if (selectType == 3) {
//			분류 리스트에 전체 북 리스트 저장
			for (int idx = 0; idx < bookList.size(); idx++) {
				filterList.add(bookList.get(idx));
			}
		}
//		분류 리스트 출력
		for (int idx = 0; idx < filterList.size(); idx++) {
			Book book = filterList.get(idx); // 대여중인지 판별할 책
			boolean isRental = isRental(book); // true:대여중, false:대여가능
			// 조건식 ? true일때 : false일때
			String bookStatus = isRental ? "[대여중]" : "[대여가능]";
			System.out.print("[" + idx + "] ");
			filterList.get(idx).bookInfo();
			System.out.println(bookStatus);
		}

		// 대출 도서 선택

		System.out.print("대여하실 책 번호를 입력해주십시오 : ");
		int selectBook = scan.nextInt();

		// 선택 도서 처리

		Book rentalBook = filterList.get(selectBook);
		boolean rental = true;
		for (int idx = 0; idx < rentalList.size(); idx++) {
			if(rentalBook == rentalList.get(idx).getBook()) {
				rental = false;
			}
		}
		if(rental) {
			Rental rent = new Rental(loginMember, rentalBook);
			rentalList.add(rent);
			System.out.println("대여 처리 되었습니다.");
			return;
		}
		System.out.println("죄송합니다. 그 책은 대여가 불가능합니다.");
	}

	private boolean isRental(Book book) { // Book의 대여 여부 판별 기능
		for (int check = 0; check < rentalList.size(); check++) {
			Book checkRental = rentalList.get(check).getBook();
			if (book == checkRental) {
				return true;
			}
		}
		return false;
	}

	// [4] 대출내역
	public void rentalReturn() {
		// 로그인 한 회원이 대여한 책의 수와 대여내역 출력
		int novelCount = 0;
		int magCount = 0;
		ArrayList<Rental> returnList = new ArrayList<>();
//확장 for문 for (Rental rent : rentalList) 첫번째 값부터 마지막 값까지 rent에 저장해서 블럭안에서 사용
//		for (int idx = 0; idx < rentalList.size(); idx++) { // 로그인 한 회원의 대여 내역 분류
//			if (rentalList.get(idx).getMember() == loginMember) {
//				returnList.add(rentalList.get(idx));
//			}
//		}
		for(Rental rent : rentalList) {
			if(rent.getMember() == loginMember ) {
				returnList.add(rent);
			}
		}
		if(returnList.size() <= 0 ) {
			System.out.println("대여 기록이 없습니다.");
			return;
		}

		for (int idx = 0; idx < returnList.size(); idx++) {
			Book returnBook = returnList.get(idx).getBook(); 
			if(returnBook instanceof Novel) {
				novelCount++;
			}else if(returnBook instanceof Magazine) {
				magCount++;
			}
			Book output = returnBook;
			System.out.print("[" + idx + "]");
			output.bookInfo();
			System.out.println("");
		}
		System.out.println("소설은 " + novelCount + "권");
		System.out.println("잡지는 " + magCount + "권");
		System.out.println("총 " + returnList.size() + "권 입니다.");
		// 반납할 책 선택
		System.out.print("반납하실 책을 선택해주십시오 : ");
		int  selectReturn = scan.nextInt();
		// 선택한 책 반납처리
		Rental returnIndex = returnList.get(selectReturn);
		rentalList.remove(returnIndex);
		returnList.get(selectReturn).getBook().bookInfo();
		System.out.println("이/가 반납처리 되었습니다.");
	}

}
