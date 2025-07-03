package day02;

import java.util.Scanner;

public class If문제1 {

	public static void main(String[] args) {
		
		//사용자로부터 나이를 입력받아서
		//나이를 판별
		// 0 ~ 12세 : 어린이 출력
		// 13 ~ 18세 : 청소년 출력
		// 19이상 : 성인 출력

		Scanner scan = new Scanner(System.in);
		
		System.out.print("나이를 입력하세요 : ");
		int age = scan.nextInt();
		if(age < 13) {
			System.out.println("어린이 입니다.");
		} else if(age < 19) {
			System.out.println("청소년 입니다.");
		} else {
			System.out.println("성인 입니다.");
		}
	}

}
