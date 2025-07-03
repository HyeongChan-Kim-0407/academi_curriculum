package day03;

public class for2 {

	public static void main(String[] args) {
		// 1 ~ 10까지 출력
		for (int i = 1; i <= 10; i++) {
//			System.out.println(i);
		}
		// 1 ~ 10까지의 합계 출력 
		/*int sum = 0;
		for (int i = 1; i <= 10; i++) { // 1 ~ 10까지 합 계산
			sum += i; // sum = sum + i;
			// 1회차 실행 sum = 0 + 1
			// 2회차 실행 sum = 1 + 2
			// 3회차 실행 sum = 3 + 3
			// ...
			// 10회차 실행 sum = 45 + 10
		}
		//합계 출력
		System.out.println(sum);
		*/
		int evenSum2 = 0;
		int evenSum = 0; // 1 ~ 10까지 짝수
		for (int i = 1; i <= 10; i++) {
			if((i % 2) == 0) {
				evenSum += i;
			} else {
				evenSum2 += i;
			}
			
			
		}
		System.out.println("짝수의 합 : " + evenSum);
		System.out.println("홀수의 합 : " + evenSum2);
		
		
		
		
		
		
	}

}
