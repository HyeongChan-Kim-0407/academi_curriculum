package day15;

import java.util.Scanner;

public class PayMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int price = 50000;
		System.out.println("결제 방식을 선택해주십시오");
		System.out.println("[1]카드 [2]포인트 ");
		int selectPayment = scan.nextInt();
		Payment pay;
		switch(selectPayment) {
		case 1:
			pay = new CardPayment();
			break;
		case 2:
			pay = new PointPayment();
			break;
		default:
			pay = new CardPayment();
		}
		
		pay.pay(price);
		
		System.out.println(CardPayment.NUMBER);
		
		System.out.println(PointPayment.NUMBER);
		
		System.out.println(Payment.NUMBER);
		
		System.out.println(CardPayment.str);
		CardPayment.str = "수정";
		System.out.println(CardPayment.str);
		
//		CardPayment.number=100;

	}

}
