package day05;

import java.util.Scanner;

public class Cafe01 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String[] menus = { "아이스아메리카노", "카페라떼", "바닐라라떼" };
		
		
		boolean cafeOpen = true;
		while (cafeOpen) {
			
			// 판매중인 메뉴 출력
			System.out.println("\n===[메뉴]===");
			
			for (int idx = 0; idx < menus.length; idx++) {
				
				System.out.println("[" + (idx + 1) + "]" + menus[idx] + " ");
			}
			// 주문할 메뉴 입력
			System.out.print("메뉴 선택>> ");
			
			int selectMenu = scan.nextInt();

			// 선택한 메뉴 이름 출력
			if (selectMenu <= menus.length && selectMenu >= 1) {
				System.out.println("선택하신 메뉴는 " + menus[(selectMenu - 1)] + "입니다.");
			} else {
				System.out.println("잘못 선택하셨습니다.");

			}

		}

	}

}
