package academiTest;

public class Main {

	public static void main(String[] args) {
		Service orderService = new Service();
		boolean open = true;
		while (open) {
			System.out.println("=====[음식 주문]=====");
			System.out.println("[0] 메뉴 등록  [1] 메뉴 주문  [2] 주문내역  [3] 주문종료");
			
			System.out.println("이용하실 서비스를 선택해주십시오.");
			int selectMenu = Service.scan.nextInt();
			switch (selectMenu) {
			
			case 0:
				orderService.register();
				break;
			case 1:
				orderService.menuList();
				break;
			case 2:
				orderService.orderList();
				break;
			case 3:
				System.out.println("주문 종료");
				open = false;
				break;
			default:
				System.out.println("잘못된 입력입니다.");
				break;
			}

		}

	}
}