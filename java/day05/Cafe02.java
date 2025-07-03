package day05;

import java.util.Scanner;

public class Cafe02 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		String[] menus = { "아메리카노", "카페라떼", "바닐라라떼" };
		int[] menuCount = new int[menus.length];
		int totalOrder = 0; //총 주문한 음료 수를 저장할 변수
		
//		int[] menuLeft = new int[menus.length];
//		
//		for(int drink = 0; drink < menus.length; drink++) {
//			
//			menuLeft[drink] += 5;
//			
//		}
		
		boolean cafeOpen = true;
		while (cafeOpen) {
//			int closecheck = 0;
//			for(int check = 0; check < menus.length; check++) {
//				if (menuLeft[check] == 0) {
//					closecheck++;
//				}
//			}
//			
//			if(closecheck == menus.length) {
//				System.out.println("음료가 모두 매진되었습니다.");
//				cafeOpen = false;
//				continue;
//			}
			
			System.out.println("\n===[메뉴]===");
			
			for (int idx = 0; idx < menus.length; idx++) {
				
				System.out.println("[" + (idx + 1) + "]" + menus[idx] + " ");
			}
			
			//종료 안내문 출력
			System.out.println("["+(menus.length + 1)+"]주문종료");
			
			// 판매중인 메뉴 출력
			System.out.print("메뉴 선택>> ");

			// 주문할 메뉴 입력
			int selectMenu = scan.nextInt();
			
			
			
			// 선택한 메뉴 이름 출력
			if ( (selectMenu <= menus.length) && (selectMenu >= 1) ) {
//				if(menuLeft[(selectMenu - 1)] == 0) {
//					System.out.println("죄송합니다 " + menus[(selectMenu - 1)] + "메뉴가 매진되었습니다.");
//					continue;
//				}
				System.out.println("선택하신 메뉴는 " + menus[(selectMenu - 1)] + "입니다.");
				menuCount[(selectMenu - 1)]++;
//				menuLeft[(selectMenu - 1)]--;
			} else if(selectMenu == (menus.length + 1)){
				cafeOpen = false;
				System.out.println("주문종료");
			} else {
				System.out.println("잘못 선택하셨습니다.");
			}
			

		}	 
		 // 세부 주문내역 출력
		 for(int order = 0; order < menus.length; order++) {
			if(menuCount[order] > 0) {
			 System.out.println(menus[order] + "는 " + menuCount[order] + "잔");
			 totalOrder += menuCount[order];
			}
		 }
		 System.out.println("총 " + totalOrder + "잔 주문하셨습니다.");
		 
//		 for(int left = 0; left < menus.length; left++) {
//			 if(menuLeft[left] > 0) {
//				 System.out.println(menus[left] + "는 " + menuLeft[left] + "잔 남아있습니다");
//			 }
//		 }

	}

	

}
