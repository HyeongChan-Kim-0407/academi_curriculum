package day04;

import java.util.Scanner;

public class while2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number = 0;
		boolean run = true;
		while(run) {
			System.out.println("[1]증가 [2]감소 [0]종료");
			System.out.print("메뉴선택 : ");
			int selectMenu = scan.nextInt();
			
			/*if( selectMenu == 1 ) {
				number++; //number 1 증가
			} else if(selectMenu == 2) {
				number--; //number 1 감소
			} else {
				break; //while문 break
			}*/
			
			switch (selectMenu) {
			case 1:
				number++;
				break;
			case 2:
				number--;
				break;
			default:
				run = false;
			}
			
		} //while문 종료 중괄호
		System.out.println("number : " + number);
	}

}
