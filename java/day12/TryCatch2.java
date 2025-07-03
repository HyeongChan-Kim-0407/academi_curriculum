package day12;

import java.util.Scanner;

public class TryCatch2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 숫자가 3개 저장된 배열을 선언
		// 배열의 3번 인덱스를 지정했을 때 발생되는 예외에 대한 처리
		try {
		int[] intArray = new int[3];
		
		System.out.print("인덱스 선택 : ");
		int selectIdx = scan.nextInt();
		
		System.out.println(intArray[selectIdx]);
		
		} catch(NumberFormatException numFor) {  // IndexOutOfBoundsException idxOutBounds
			System.out.println("존재하지 않는 인덱스입니다.");
		} finally {
			// 예외와 상관없이 무조건 실행되는 코드 (생략 가능)
			System.out.println("finally 코드 실행");
		}
		
		System.out.println("try-catch 이후 실행");

	}

}
