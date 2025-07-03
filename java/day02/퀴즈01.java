package day02;

import java.util.Scanner;

public class 퀴즈01 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 1. score 변수 선언 70으로 초기화
		// 2. 사용자로부터 숫자를 입력 받는다.
		// 3. 사용자가 입력한 숫자가 score 보다 큰 값인지 비교 결과 값을 출력 (true, false)
		
		int score = 70;
		System.out.println("숫자 입력 : ");
		int userScore = scan.nextInt();
		boolean scoreResult = (userScore > score); 
		System.out.println(scoreResult);
		
		// 1. name 변수를 선언하고 자기 이름으로 초기화
		// 2. 사용자로부터 이름을 입력받는다.
		// 3. 사용자가 입력한 이름과 name이 같은지 비교 결과를 출력 (true, false)
		
		String name = "KHC";
		System.out.println("이름 입력 : ");
		String userName = scan.next();
		boolean nameResult = (name.equals(userName));
		System.out.println(nameResult);
				
				
	}

}
