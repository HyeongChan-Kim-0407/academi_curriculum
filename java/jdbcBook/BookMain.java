package jdbcBook;

public class BookMain {

	public static void main(String[] args) {
		
		BookService bsvc = new BookService();
		boolean run = true;
		
		while(run) {
			System.out.println("\n=======================================");
			System.out.println("[1]도서검색 [2]도서대여 [3]대여확인 [4]도서반납"); 
			System.out.println("[5]도서등록 [6]회원등록 [7]회원목록 [0]종료");
			System.out.println("=======================================");
			System.out.print("이용하실 서비스를 선택해주십시오 : ");
			// 검색기능 < 모든 책에 대한 현재 정보 출력 / 도서대여 < 대여가능한 책만 출력 
			// 대여확인 < 대여한 모든 목록 출력 / 도서반납 < 미반납중인 책 출력 후 반납처리
			
			int selMenu = bsvc.scan.nextInt();
			switch(selMenu) {
			case 1:
				bsvc.searchBook();
				break;
			case 2:
				bsvc.rentalBook();
				break;
			case 3:
				bsvc.checkRental();
				break;
			case 4:
				bsvc.returnBook();
				break;
			case 5:
				bsvc.registBook();
				break;
			case 6:
				bsvc.joinMember();
				break;
			case 7:
				bsvc.checkMember();
				break;
			case 0:
				run = false;
				break;	
			}
			
			
			
			
			
		}
		
	}

}
