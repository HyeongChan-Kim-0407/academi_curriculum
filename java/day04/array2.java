package day04;

import java.util.Scanner;

public class array2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 타입[] 배열이름 = new 타입[크기(숫자)];
		int[] scores = new int[5]; // 5개의 점수를 저장할 배열
		scores[0] = 80; // 80을 scores 배열의 0번 인덱스에 저장
		scores[1] = 85; // 85를 scores 배열의 1번 인덱스에 저장
		scores[2] = 90; // 90을 scores 배열의 2번 인덱스에 저장
		scores[3] = 95; // 95을 scores 배열의 3번 인덱스에 저장
		scores[4] = 100;// 100을 scores 배열의 4번 인덱스에 저장
		
		// scores 배열의 모든 값을 출력
		// for(초기화식; 조건식(배열의 길이 사용); 증감식)
		// 배열이름.length :: 배열의 크기(길이)
		for( int idx = 0; idx < scores.length; idx++ ) {
			// idx = 0 ~ 4
			System.out.println(scores[idx]);
		}
		
		System.out.println("\n scores2");
		int[] scores2 = new int[5];
		// scores2배열의 점수 입력
		for( int idx = 0; idx < scores2.length; idx++ ) {
			System.out.print((idx+1)+"번 과목 점수 입력");
			int inputscore = scan.nextInt(); // 사용자가 입력한 숫자
				// 입력한 점수가 0 ~ 100 사이의 숫자인지 판별
				/*if (scores2[idx] > 100 || scores2[idx] < 0) { //정상 범위 이외의 점수를 검출
					System.out.println("점수를 잘못 입력하셨습니다. 다시 입력해주십시오 ");
					idx--;
				*/
				if( 0 <= inputscore && inputscore <= 100) { //정상 범위 내의 점수를 검출
					scores2[idx] = inputscore; // 사용자가 입력한 숫자가 if문에서 설정한 범위의 숫자라면 배열에 숫자를 저장
				}else {
					System.out.println("점수를 잘못 입력하셨습니다. 다시 입력해주십시오 ");
					idx--;
			  }
			
		}
		
		// scores2의 50점 이상인 점수만 출력
		for (int idx = 0; idx < scores2.length; idx++) {
			if (scores2[idx] >= 50) {
				System.out.println((idx+1)+"번 과목 점수" + scores2[idx]);
			}

		}
		
		
		
				
				
				

	}

}
