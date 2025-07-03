package day06;

import java.util.Scanner;

public class Cafe03 { // 선택하고 f2 클래스 이름 바꾸기

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		// 1. 품절된 메뉴 표시 x or 품절표시, 재고수량 표시 -ok
		// 2. 메뉴 여러 잔 주문 - ok

		String[] menus = { "아이스아메리카노", "카페라떼", "바닐라라떼", "아이스티" };
		// 아메리카노 1500원 카페라떼 2500원 바닐라라떼 3000원 아이스티 2000원
		int[] drinkleft = new int[menus.length]; // 메뉴 별 재고

		// 재고 입력

		for (int drinks = 0; drinks < menus.length; drinks++) {

			System.out.print(menus[drinks] + "의 재고: ");

			int enter = scan.nextInt();

			drinkleft[drinks] = enter;

		}

		boolean cafeOpen = true;
		while (cafeOpen) {
			// 예정) 모든 재고가 0이 되었을 때, 자동으로 영업종료. "오늘 영업이 종료되었습니다. "
			
			
			
			System.out.println("\n===[주문가능 메뉴]===");

			for (int idx = 0; idx < menus.length; idx++) { // 재고 입력

				if (drinkleft[idx] > 0) {

					System.out.println("[" + (idx + 1) + "]" + menus[idx] + " ( 주문 가능 수량 : " + drinkleft[idx] + "잔 ) ");
				}
//				else {
//					System.out.println("[" + (idx + 1) + "]" + menus[idx] + " ( 재고 없음 ) ");
//				}
			}

			System.out.println("[" + (menus.length + 1) + "]주문종료");

			// 판매중인 메뉴 출력
			System.out.print("메뉴 선택 >> ");
			
			// 예정) 모든 재고가 0이 되었을 때, 자동으로 영업종료. "오늘 영업이 종료되었습니다. "
			
			
			// 주문할 메뉴 입력
			int selectMenu = scan.nextInt();

			// 선택한 메뉴 이름 출력
			if (selectMenu == (menus.length + 1)) { // 주문종료 입력 시

				cafeOpen = false;
				System.out.println("주문종료");

			} else if (selectMenu <= menus.length && selectMenu >= 1) { // 주문 메뉴 입력시
				if (drinkleft[(selectMenu - 1)] > 0) { // 재고가 남아있는 (0보다 큰) 경우
					System.out.println("선택하신 메뉴는 " + menus[(selectMenu - 1)] + "입니다.");

					System.out.print("주문하실 수량을 입력해주십시오. >> ");

					int order = scan.nextInt();
					if (order <= drinkleft[(selectMenu - 1)]) { // 주문 수량이 재고보다 작거나 같은 경우
						//if (drinkleft[(selectMenu - 1)] > 0) { // 재고가 0보다 큰 경우
							// 선택한 메뉴의 재고 1씩 감소
							drinkleft[(selectMenu - 1)] -= order;
							System.out.println("선택하신 메뉴가 주문되었습니다.");

						//}
//						else {
//
//							System.out.println("죄송합니다 선택하신 메뉴가 현재 품절되었습니다. 다른 메뉴를 주문해주십시오.");
//
//						}
					} else {
						System.out.println("죄송합니다 주문 가능 수량보다 많은 양은 주문하실 수 없습니다. 다시 주문해주십시오.");
					}
				} else {
					System.out.println("죄송합니다. 주문하신 메뉴는 재고가 없습니다.");
				}
			} else {
				System.out.println("잘못 선택하셨습니다.");

			}

		}

	}

}
