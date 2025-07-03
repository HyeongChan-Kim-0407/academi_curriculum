package jdbc_orders;

public class OrderMain {

	public static void main(String[] args) {

		OrderService odsvc = new OrderService();
		boolean run = true;
		while (run) {

			System.out.println("\n====[ 메 뉴 ]====");
			Member loginMember = odsvc.getloginMember();
			if (loginMember == null) {
				System.out.println("[1]회원가입 [2]로그인 [0]종료");
			} else {
				System.out.println("[ " + loginMember.getMid() + " 님 ] 로그인");
				System.out.println("[3]상품주문 [4]주문내역 [5]로그아웃");
			}
			// 메뉴 선택
			System.out.print("선택 : ");
			// 선택 기능 호출
			int selectMenu = odsvc.scan.nextInt();
			switch (selectMenu) {
			case 1:
				odsvc.joinMember();
				break;
			case 2:
				odsvc.loginMember();
				break;
			case 3:
				odsvc.orderProduct();
				break;
			case 4:
				odsvc.orderList();
				break;
			case 5:
				odsvc.logoutMember();
				break;
			case 100:
				odsvc.memberList();
				break;
			case 0:
				run = false;
				break;
			}
			

		}
		
		System.out.println("프로그램 종료");
		
	}

}
