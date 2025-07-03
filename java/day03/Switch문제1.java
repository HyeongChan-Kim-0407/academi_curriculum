package day03;

import java.util.Scanner;

public class Switch문제1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 계산기
		// 1. 첫번째 숫자(정수)를 입력
		// 2. 연산자(문자)를 입력
		// 3. 두번째 숫자(정수)를 입력
		// 4. 선택된 연산자에 따라서 두 수의 연산 결과를 출력
		
		System.out.println("첫번째 숫자를 입력해주세요.");
		int num1 = scan.nextInt();
		
		System.out.println("연산자를 입력해주세요. (+, -, *, /, %) ");
		String cal = scan.next();
		
		System.out.println("두번째 숫자를 입력해주세요.");
		int num2 = scan.nextInt();
		
		switch(cal){
			case "+":
				System.out.println("결과값은 " + (num1 + num2));
				break;
			case "-":
				System.out.println("결과값은 " + (num1 - num2));
				break;
			case "*":
				System.out.println("결과값은 " + (num1 * num2));
				break;
			case "/":
				if(num2 == 0) {
					System.out.println("수는 0으로 나눌 수 없습니다.");
				} else {System.out.println("결과값은 " + (num1 / num2));}
				break;
			case "%":
				System.out.println("결과값은 " + (num1 % num2));
				break;
			default:
				System.out.println("연산자를 확인해주세요.");
		}
		
		
		
		
	}

}
