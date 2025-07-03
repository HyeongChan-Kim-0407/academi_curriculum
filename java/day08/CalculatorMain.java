package day08;

import java.util.Scanner;

public class CalculatorMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Calculator calc01 = new Calculator();
		
		System.out.print("첫번째 숫자를 입력해주십시오 : ");
		int firstNumber = scan.nextInt();
		// 연산자 입력 >> +
		System.out.print("두번째 숫자를 입력해주십시오 : ");
		int secondNumber = scan.nextInt();
		
		// Calcurator의 덧셈기능 메소드 호출
		int plusResult = calc01.plus(firstNumber, secondNumber);
		System.out.println("덧셈 연산 결과 : " + plusResult);
		
		double divideResult = calc01.divide(firstNumber, secondNumber);
		System.out.println("나눗셈 연산 결과 : " + divideResult);
		
		
		
		
		// 메소드를 호출
		// Calculator 클래스에 정의된 voidMethod() 호출
		calc01.voidMethod();
		// intMethod() 호출
		int a = calc01.intMethod();
		System.out.println("a : " + a);
		
		
		
		
		
		
		

	}

}
