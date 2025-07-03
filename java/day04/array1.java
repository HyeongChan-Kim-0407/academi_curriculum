package day04;

public class array1 {

	public static void main(String[] args) {
		//변수 선언
		int x;
		// 배열은 하나의 변수에 여러 개의 값을 저장
		//배열(크기가 3인 배열 생성)
		int[] numbers = new int[3];
				
		//배열 선언과 동시에 초기화
		int[] scores = { 80, 70, 100, 85, 75 };
		
		//배열 안의 값을 사용 ( 배열의 인덱스 )
		System.out.println("scores의 0번 인덱스");
		System.out.println(scores[0]);
		System.out.println("scores의 4번 인덱스");
		System.out.println(scores[4]);
		System.out.println("scores의 5번 인덱스");
		//System.out.println(scores[5]); << 예외 발생
		// 배열의 크기 : 배열이름.length
		System.out.println("배열의 크기 : " + scores.length);
		
		System.out.println("numbers의 0번 인덱스");
		System.out.println(numbers[0]);
		System.out.println("numbers의 크기 : " + numbers.length);
		
		// 배열에 값을 저장
		// numbers[] = [ ][ ][ ]
		numbers[0] = 100; // [100][ ][ ]
		numbers[2] = 200; // [100][ ][200]
		//numbers[3] = 300; << 예외 발생
		System.out.println(numbers[0]);
		System.out.println(numbers[1]);
		System.out.println(numbers[2]);
		/*
		for(int i = 0; i < 배열이름.length; i++) {
			반복실행할 코드
		}
		*/
		System.out.println("for문 사용");
		for(int idx = 0; idx < numbers.length; idx++) {
			System.out.println(numbers[idx]);
		}
		System.out.println("\nscores배열 값-for문");
		for(int idx = 0; idx < scores.length; idx++) {
			System.out.println(scores[idx]);
		}
		
		
		
	}

}
