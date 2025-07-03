package jdbcBook;

import java.util.ArrayList;
import java.util.Scanner;

public class BookService {

	Scanner scan = new Scanner(System.in); // BookMain 클래스에서도 사용해야하므로 private X
	private BookDao bdao = new BookDao(); // Service 클래스에서만 사용할 것이므로 private O
	private RentalDao rdao = new RentalDao();
	private MemberDao mdao = new MemberDao();

	public void registBook() {
		System.out.println("도서등록");
//		등록할 책 정보 입력
		scan.nextLine();
		System.out.print("도서명 : ");
		String title = scan.nextLine();
		System.out.print("저자 : ");
		String author = scan.nextLine();
		System.out.print("도서종류[소설, 잡지, 코믹] : ");
		String type = scan.next();
//		입력한 책 정보로 Book 객체 생성
		Book book = new Book(title, author, type);
//		생성된 Book 객체를 통해 BookDao 클래스의 메소드를 이용해서 데이터베이스의 Book 테이블에 데이터를 INSERT
		int insertResult = bdao.insertBook(book);
//		반환된 INSERT 처리 결과를 통해 도서등록 결과 판별
		if (insertResult > 0) {
			System.out.println("도서 등록 완료");
		} else {
			System.out.println("도서 등록 실패");
		}
	}

	public void searchBook() {
		System.out.println("도서검색");
		ArrayList<Book> bookList = getBookList(false);
		
		/* Book 목록 출력 */
		if (bookList.isEmpty()) {
			System.out.println("일치하는 도서가 없습니다");
			return;
		}

		System.out.println("도서종류, 도서명, 대여가능여부");
		for (int idx = 0; idx < bookList.size(); idx++) {
			Book book = bookList.get(idx);
			System.out.println("[" + book.getBtype() + "] " + book.getBtitle() + " (" + book.getBstate() + ")");

		}

	}

	public void rentalBook() {
		System.out.println("도서대여");
		ArrayList<Book> bookList = getBookList(true);
		if (bookList.isEmpty()) {
			System.out.println("대여가 가능한 도서를 조회할 수 없습니다.");
			return;
		}
		int index = 1;
		for (Book book : bookList) {
			System.out.println("[" + index++ + "] [" + book.getBtype() + "] " + book.getBtitle());
		}
		System.out.println("대여하실 도서를 선택해주십시오");
		int selidx = scan.nextInt();
		Book selBook = bookList.get(selidx - 1);
		System.out.println("선택하신 도서는 " + selBook.getBtitle() + "입니다.");
		System.out.println("대여하시겠습니까? [1]예 [2]아니오 ");
		int selMenu = scan.nextInt();
		if (selMenu == 1) {
			System.out.print("아이디를 입력해주십시오 : ");
			String inputId = scan.next();
			System.out.print("비밀번호를 입력해주십시오 : ");
			String inputPw = scan.next();
			Member rentalMember = mdao.selectMemberByIdAndPw(inputId, inputPw);
			if (rentalMember == null) {
				System.out.println("아이디 혹은 비밀번호를 확인해주십시오.");
				return;
			} else {
				int rentalCount = rdao.selectRentalCountByLoginMember(rentalMember, true);
				if (rentalCount < 0) {
					System.out.println("대여 처리 과정에서 문제가 생겼습니다 다시 시도해주십시오.");
					return;
				} else if (rentalCount > 2) {
					System.out.println("죄송합니다 최대 대여가능 도서 수는 3권입니다.");
					return;
				} else {
					int rentalResult = rdao.insertRentalAndUpdateBstate(selBook, rentalMember);
					if (rentalResult > 0) {
						System.out.println("대여처리 되었습니다.");
						System.out.println(rentalMember.getMid() + "님의 현재 대여중인 도서는 " + (rentalCount + 1) + "권 입니다.");
						return;
					} else {
						System.out.println("대여에 실패했습니다.");
					}
				}
			}
		} else if (selMenu == 2) {
			System.out.println("메인메뉴로 돌아갑니다.");
			return;
		}

	}

	private ArrayList<Book> getBookList(boolean checkState) {
		System.out.println("[1]전체 도서 검색 [2] 도서 제목 검색 [3] 도서 종류 검색 [4] 인기 도서 검색 ");
		System.out.print("선택 : ");
		int selMenu = scan.nextInt();
		ArrayList<Book> bookList = new ArrayList<>();
		if (selMenu == 1) {
			/* [1] SELECT * FROM BOOK */
			bookList = bdao.selectBook(checkState);
		} else if (selMenu == 2) {
			/* [2] SELECT * FROM BOOK WHERE BTITLE LIKE */
			System.out.print("찾으시는 책의 제목을 입력해주십시오 : ");
			String findTitle = scan.next();
			bookList = bdao.selectBookByTitle(findTitle, checkState);
		} else if (selMenu == 3) {
			/* [3] SELECT * FROM BOOK WHERE BTYPE IN */
			System.out.println("찾으시는 책의 종류를 입력해주십시오[소설, 잡지, 코믹] ");
			String findType = scan.next();
			bookList = bdao.selectBookByType(findType, checkState);
		} else if (selMenu == 4){
			bookList = bdao.selectBookByCount(checkState);
		} else {
			System.out.println("잘못된 입력입니다 다시 이용해주십시오.");
			return bookList;
		}
		return bookList;
	}

	public void checkRental() {

		System.out.println("대여확인");

		ArrayList<Rental> allRentalList = getRentalList(false);

		if (allRentalList == null) {
			return;
		}

		System.out.println("도서번호, 도서명, 대여일자, 반납일자");
		for (Rental rental : allRentalList) {

			System.out.println(rental.getBook().getBid() + ", " + rental.getBook().getBtitle() + ", "
					+ rental.getRentaldate() + ", " + rental.getReturndate());
		}

		System.out.println("메인메뉴로 돌아가려면 아무 숫자 혹은 문자를 입력해주십시오");
		String toMain = scan.next();

		if (toMain != null) {
			return;
		}
	}

	public void returnBook() {

		System.out.println("도서반납");

		ArrayList<Rental> allRentalList = getRentalList(true);

		if (allRentalList == null) {
			return;
		}

		if (allRentalList.size() == 0) {
			System.out.println("현재 대여중인 도서가 없습니다.");
			return;
		}

		System.out.println("도서번호, 대여일자, 반납일자");
		for (Rental rental : allRentalList) {

			System.out.println(rental.getBook().getBid() + ", " + rental.getBook().getBtitle() + ", "
					+ rental.getRentaldate() + ", " + rental.getReturndate());
		}

		System.out.print("반납하실 도서 번호를 입력해주십시오 : ");
		int returnBid = scan.nextInt();

		int returnResult = rdao.updateRentalAndBstate(returnBid);

		if (returnResult != 1) {
			System.out.println("반납 과정에서 문제가 생겼습니다 다시 시도해주십시오");
			return;
		}

		System.out.println("반납이 완료되었습니다.");

	}

	private ArrayList<Rental> getRentalList(boolean check) {

		System.out.print("아이디를 입력해주십시오 : ");
		String id = scan.next();
		System.out.print("비밀번호를 입력해주십시오 : ");
		String pw = scan.next();

		Member loginMember = mdao.selectMemberByIdAndPw(id, pw);
		if (loginMember == null) {
			System.out.println("아이디 혹은 비밀번호를 확인해주십시오.");
			return null;
		}

		ArrayList<Rental> allRentalList = rdao.selectRentalById(loginMember, check);

		return allRentalList;

	}

	public void joinMember() {

		System.out.println("회원등록");

		System.out.print("사용하실 아이디를 입력해주십시오.");
		String mid = scan.next();
		System.out.print("사용하실 비밀번호를 입력해주십시오.");
		String mpw = scan.next();
		System.out.print("사용중인 이메일을 입력해주십시오.");
		String memail = scan.next();
		System.out.print("사용중인 휴대폰번호를 입력해주십시오.");
		String mphone = scan.next();
		System.out.print("거주중인 지역을 입력해주십시오.");
		String maddr = scan.next();

		Member joinMember = new Member(mid, mpw, memail, mphone, maddr);

		int insertResult = mdao.insertJoinMember(joinMember);

		if (insertResult != 1) {
			System.out.println("회원가입이 실패하였습니다");
		}

		System.out.println("회원가입이 완료되었습니다.");
	}

	public void checkMember() {

		System.out.println("회원목록");

		ArrayList<Member> memberList = mdao.selectMemberWithRental();

		System.out.println("회원, 총 대여권수, 현재 대여권수");

		for(Member member : memberList) {
			System.out.println("아이디 : " + member.getMid() + ", 비밀번호 : " + member.getMpw() + ", 이메일 : " + member.getMemail()
			+ ", 전화번호 : " + member.getMphone() + ", 주소지 : " + member.getMaddr() + ", 가입일 : " + member.getMjoindate()
			+ ", 총대여권수 : " + member.getTotalRental() + "권, 현재대여권수 : " + member.getRental() + "권 ");
		}

		System.out.println("메인메뉴로 돌아가려면 아무 숫자, 문자를 입력해주십시오.");
		String toMain = scan.next();

		if (toMain != null) {
			return;
		}

	}

}
