package day03;

import java.util.Scanner;

public class Switch2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("메뉴를 선택해주세요.");
		System.out.println("[1]성인 [2]청소년 [3]어린이");
		System.out.print("선택>>");
		int selectMenu = scan.nextInt();
		switch(selectMenu) {
			case 1:
				System.out.println("성인은 20000원 입니다.");
				break;
			case 2:
				System.out.println("청소년은 15000원 입니다.");
				break;
			case 3:
				System.out.println("어린이는 5000원 입니다.");
				break;
			default:
				System.out.println("잘못 선택하셨습니다.");
		}
		
		if(selectMenu == 1) {
			
		} else if(selectMenu == 2) {
			
		} else if(selectMenu == 3) {
			
		} else {
			
		}
		
		

	}

}
