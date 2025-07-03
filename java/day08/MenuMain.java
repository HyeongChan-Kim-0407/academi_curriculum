package day08;

import java.util.Scanner;

public class MenuMain {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		MenuInfo cafe01 = new MenuInfo();
		
		// 모든 필드는 private 선언
		
		// 1. 이름, 가격, 재고를 입력받는다.
		// 2. 입력받은 정보는 MenuInfo객체에 저장
		// 3. 가격은 1500 미만의 값은  1500원으로 저장. 
		// 5000 초과하는 값은 5000으로 저장.
		
		System.out.print("메뉴의 이름을 입력해주십시오. : ");
		cafe01.setMenuName(scan.next());
		
		System.out.print("메뉴의 가격을 입력해주십시오. : ");
		cafe01.setMenuPrice(scan.nextInt());
		
		System.out.print("메뉴의 재고를 입력해주십시오. : ");
		cafe01.setMenuLeft(scan.nextInt());
		
		System.out.println(cafe01.getMenuName());
		System.out.println(cafe01.getMenuPrice());
		System.out.println(cafe01.getMenuLeft());
		
		MenuInfo cafe02 = new MenuInfo();
		cafe02.setMenuName("카페라떼");
		cafe02.setMenuPrice(2500);
		cafe02.setMenuLeft(3);
		
		
		MenuInfo[] cafes = new MenuInfo[4];
		
			
		cafes[0] = cafe01;
		cafes[1] = cafe02;
		
		// 첫번째 메뉴의 이름 출력
		System.out.println(cafes[0].getMenuName());
		// 두번째 메뉴의 이름 출력
		System.out.println(cafes[1].getMenuName());
		
		for(int idx = 0; idx < cafes.length; idx++) {
			if(cafes[idx] == null) {
				continue;
			}
			System.out.println(idx + " " + cafes[idx].getMenuName() + " " + cafes[idx].getMenuPrice() + "원 ( 재고 : " + cafes[idx].getMenuLeft() + "잔 )");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
