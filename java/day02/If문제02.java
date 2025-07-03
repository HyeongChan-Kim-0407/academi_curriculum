package day02;

import java.util.Scanner;

public class If문제02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 공연 관람을 위한 티켓 구매
		// 티켓 가격
		// 어린이 (0 - 12) : 5000, 청소년 ( 13 - 18 ) : 10000, 성인 ( 19- ) : 20000
		// 나이를 판별하고 티켓 구매 후 잔액 출력.
		System.out.println("갖고있는 현금을 입력해주세요 : ");
		int myMoney = scan.nextInt(); // 현재 가지고 있는 현금.
		System.out.print("나이를 입력해주세요 : ");
		int userAge = scan.nextInt();
		int ticket = 0;
		String customer = "";
		if (userAge >= 19) {
			customer = "성인";
			ticket = 20000;
		} else if (userAge >= 13) {
			customer = "청소년";
			ticket = 10000;
		} else {
			customer = "어린이";
			ticket = 5000;
		}

		if (myMoney >= ticket) {
			
			System.out.println("귀하께서는 " + customer + "이시고 남은 잔액은 : " + (myMoney - ticket) + "원 입니다.");
			
		} else {
			
			System.out.println("현금이 부족하여 티켓을 구매하지 못합니다.");
			
		} 
		
	}
}
