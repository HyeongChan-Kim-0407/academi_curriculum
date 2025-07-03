package day06;

import java.util.Scanner;

public class CafeOrder {

	public static void main(String[] args) {
		// 카페 주문 시스템
		// 1. 메뉴는 이름, 가격, 재고 항목 구성
		// 2. 품절된 메뉴 주문 x
		// 3. 메뉴 재고보다 많은 수량 주문 x
		// 4. 주문 종료 시 총 주문 수량, 메뉴 별 주문 수 및 메뉴 별 총액, 총 주문금액 순으로 출력

		Scanner scan = new Scanner(System.in);

		String[] menus = { "아메리카노", "카페라떼", "바닐라라떼", "아이스티" };

		int[] drinkLeft = new int[menus.length];
		int[] drinkPrice = new int[menus.length];
		int[] drinkOrder = new int[menus.length];
		int totalPrice = 0;
		int totalOrder = 0;

		for (int drinks = 0; drinks < menus.length; drinks++) {

			System.out.print(menus[drinks] + "의 재고: ");

			int enterLeft = scan.nextInt();

			drinkLeft[drinks] = enterLeft;

		}

		for (int price = 0; price < menus.length; price++) {

			System.out.print(menus[price] + "의 가격: ");

			int enterPrice = scan.nextInt();

			drinkPrice[price] = enterPrice;

		}

		boolean cafeOpen = true;
		while (cafeOpen) {

			System.out.println("\n===[주문가능 메뉴]===");

			for (int idx = 0; idx < menus.length; idx++) { // 재고 입력

				if (drinkLeft[idx] > 0) {

					System.out.println("[" + (idx + 1) + "]" + menus[idx] + " " + drinkPrice[idx] + "원" + " ( 주문 가능 수량 : " + drinkLeft[idx] + "잔 ) ");
				} else {
					System.out.println("[" + (idx + 1) + "]" + menus[idx] + " ( 재고 없음 ) ");
				}

			}
			System.out.println("[" + (menus.length + 1) + "]주문종료");

			System.out.print("메뉴 선택 >> ");

			int selectMenu = scan.nextInt();

			if (selectMenu == (menus.length + 1)) { // 주문종료 입력 시

				cafeOpen = false;
				System.out.println("주문종료");

			} else if (selectMenu <= menus.length && selectMenu >= 1) { // 메뉴 주문시 정상범위 내 숫자 입력시 주문 진행
				if (drinkLeft[(selectMenu - 1)] > 0) { // 재고가 남아있는 (0보다 큰) 경우
					System.out.println("선택하신 메뉴는 " + menus[(selectMenu - 1)] + "입니다.");

					System.out.print("주문하실 수량을 입력해주십시오. >> ");

					int order = scan.nextInt();
					if (order <= drinkLeft[(selectMenu - 1)]) { // 주문 수량이 재고보다 작거나 같은 경우

						drinkLeft[(selectMenu - 1)] -= order;
						drinkOrder[(selectMenu - 1)] += order;
						totalOrder += order;
						totalPrice += (drinkPrice[(selectMenu - 1)] * order);
						System.out.println("선택하신 메뉴가 주문되었습니다.");

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
		System.out.println("총 " + totalOrder + "잔 주문하셨습니다.");
		for (int order = 0; order < menus.length; order++) {
			if (drinkOrder[order] > 0) {
				System.out.println(
						menus[order] + " " + drinkOrder[order] + "잔 " + (drinkPrice[order] * drinkOrder[order]) + "원");
			}
		}
		System.out.println("총 주문금액 " + totalPrice + "원 입니다.");
	}
}
