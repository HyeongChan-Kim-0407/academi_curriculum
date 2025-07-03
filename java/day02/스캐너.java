package day02;

import java.util.Scanner;

public class 스캐너 {

	public static void main(String[] args) {
		
		// 사용자 입력 (Scanner 사용)
		// import 단축키 Ctrl + Shift + O
		Scanner scan = new Scanner(System.in);
		// 숫자 입력
		System.out.println("숫자를 입력해주세요 > ");
		int inputNumber = scan.nextInt();
		System.out.println(inputNumber);
		
		// 문자 입력
		System.out.println("문자를 입력해주세요 > ");
		String inputString = scan.next();
		System.out.println(inputString);
		

	}

}
