package bookRental_1;

public class RantalMain {

	public static void main(String[] args) {
		RentalService rentalService = new RentalService();
		rentalService.start();
		boolean run = true;
		while(run) {
			Member loginMember = rentalService.getLoginMember();
			System.out.println("\n=====메뉴=====");
			if (loginMember == null) {
				System.out.println("[1]회원가입 [2]로그인 [0]종료");
			} else {
				System.out.println("[" + loginMember.getMid() + "]로그인");
				System.out.println("[3]도서대출 [4]대출내역 [5]로그아웃");
			}
			System.out.print("메뉴선택>>");
			int selMenu = RentalService.scan.nextInt();
			switch(selMenu) {
			case 3:
				rentalService.rentBook();
				break;
			}
		}

	}

}





