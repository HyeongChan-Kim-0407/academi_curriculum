package day05;

import java.util.Scanner;

public class quiz01 {

	public static void main(String[] args) {
		// 1. 크기가 10인 int형 배열을 선언
		// 2. 모든 인덱스에 사용자로부터 입력받은 숫자를 저장
		// 3. 단, 사용자의 입력 숫자는 1 ~ 10 사이의 숫자만 입력받도록 한다.
		// 4. 배열의 저장되는 숫자는 중복이 없도록 한다.
		
		Scanner scan = new Scanner(System.in);

		int[] numbers = new int[10];

		for (int idx = 0; idx < numbers.length; idx++) {
			System.out.print((idx + 1) + "번째 숫자를 입력해주십시오. ");
			int inputNumber = scan.nextInt();

			if (inputNumber < 1 || inputNumber > 10) { // 1 ~ 10 범위 밖의 숫자 검출
				System.out.println("1 ~ 10 사이의 숫자만 입력해주십시오.");
				idx--;
				continue;
			}
			boolean check = false;
			for (int i = 0; i < numbers.length; i++) { 
				if (numbers[i] == inputNumber) {
					check = true;
					break;
				}
			}
			if (check) { // check의 값이 true면 실행 
				System.out.println("중복된 숫자입니다.");
				idx--;
				continue;
			}
			numbers[idx] = inputNumber;

		}
		for (int a = 0; a < numbers.length; a++) {
			System.out.println(numbers[a]);
		}

	}

}





