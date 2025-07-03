package day02;

public class If01 {

	public static void main(String[] args) {
		
		// 제어문 - 조건문 ( if문 ) and 반복문
		/*
		 if( 조건식 : true, false ) {
		 
		 	조건식이 true일 경우에 실행
		 
		 }
		 */
		int number = 10;
		System.out.println("if문 전");
		boolean isTrue = number < 5;
		if(isTrue) {
			System.out.println("조건이 true이면 실행");
		}
		System.out.println("if문 후");
		
		/*
		 if(조건식1){
		 	조건식1이 true이면 실행
		 } else if(조건식2){
		 	조건식1은 false이고 조건식2가 true이면 실행
		 } else if(조건식3){
		 	조건식1은 false이고
		 	조건식2도 false이고
		 	조건식3이 true이면 실행
		 } else {
		 		 위의 모든 조건이 false일 때 실행
		 }
		 */
		// 점수별 등급 출력
		int score = 75;
		/*System.out.println("학점 출력 전");
		if(score >= 90) { //조건 : 90점 이상이면
			System.out.println("A학점입니다.");
		} else {
			System.out.println("A학점이 아닙니다.");
		}
		System.out.println("학점 출력 후");*/
		
		System.out.println("학점 출력 전");
		if(score >= 90) {
			System.out.println("A학점 입니다.");
		} else if(score >= 80) {
			System.out.println("B학점 입니다.");
		} else if(score >= 70) {
			System.out.println("C학점 입니다.");
		} else {
			System.out.println("D학점 입니다.");
		}
		System.out.println("학점 출력 후");
	
		}

}
