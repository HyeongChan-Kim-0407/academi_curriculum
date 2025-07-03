package day04;

import java.util.Scanner;

public class while1 {

	public static void main(String[] args) {
		// 제어문 - 반복문(while, do ~ while)
		/*
		 while(조건식) { 
		  	//조건식이 true일 때 실행 
		 }
		 */
		int i = 1;
		while( i <= 5 ) {
			System.out.println(i);
			i++;
		}
		
		Scanner scan = new Scanner(System.in);
		/*String inputStr = ""; // 사용자 입력 값 저장
		// inputStr.equals("x") >> ""와 "x" 문자가 같은지 비교 = false
		// ! : true를 false로, false를 true로 > NOT 연산
		
		while( !inputStr.equals("x")) {
			System.out.println("문자 입력(x : 종료)");
			inputStr = scan.next();
			//System.out.println(inputStr);
		}
		System.out.println("while문 종료.");
		*/
		
		String inputStr = "";
		while(true) {
			System.out.println("문자 입력(x : 종료)");
			inputStr = scan.next();
			// break를 이용해서 반복을 중단
			if(inputStr.equals("x")) { //inputStr이 x라면 break 실행
				break;
			}
		}
		System.out.println("while문 종료.");
		
		//do - while
		int x = 10;
		do {
			//반복실행할 코드
			System.out.println("x : " + x);
		} while(x < 5); //do - while문의 반복 조건식
		System.out.println("do - while문 종료");
		
		System.out.println("while문");
		while(x < 5) {
			System.out.println("x : " + x); 
		}
		System.out.println("while문 종료");
		
		
		
		
		

	}

}
