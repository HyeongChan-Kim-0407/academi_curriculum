package bookRental;

public class RentalMain {

	public static void main(String[] args) {

		RentalService rentalService = new RentalService();
		rentalService.start();

		Boolean run = true;

		while (run) {
			Member loginMember = rentalService.getLoginMember();
			System.out.println("\n====[ 메 뉴 ]====");
			if (loginMember == null) {
				System.out.println("[1]회원가입 [2]로그인 [0]종료");
			} else {
				System.out.println("[ " + loginMember.getmId() + " 님 ] 로그인");
				System.out.println("[3]도서대출 [4]대출내역 [5]로그아웃");
			}

			System.out.print("메뉴 선택 : ");
			int selectMenu = RentalService.scan.nextInt();

			switch (selectMenu) {

			case 1:
				rentalService.register();
				break;
			case 2:
				rentalService.login();
				break;
			case 3:
				rentalService.rentalBook();
				break;
			case 4:
				rentalService.rentalReturn();
				break;
			case 5:
				rentalService.logout();
				break;
			case 0:
				System.out.println("도서대출시스템 종료");
				run = false;
			}

		}

	}

}
