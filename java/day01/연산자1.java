package day01;

public class 연산자1 {

	public static void main(String[] args) {
		
		//산술연산자 ( +, -, *, /, % )
		int num1 = 10; 
		int num2 = 20;
		int result = num1 + num2; // num1 과 num2를 합한 결과를 result에 저장
		System.out.println(result); // console에 result를 출력
		
		//나누기 (/)
		int totalScore = 250;
		int count = 3;
		int avgResult = totalScore / count;
		System.out.println("나누기 결과 : " + avgResult);
		
		// 결과를 저장할 변수만 double로 선언
		double avgResult2 = totalScore / count;
		System.out.println("2.나누기 결과 : " + avgResult2);
		
		// totalScore를 실수로 형 변환 후 연산
		double avgResult3 = (double)totalScore / count;
		System.out.println("3.나누기 결과 : " + avgResult3);
		
		//나머지 (%)
		int num3 = 10;
		int result2 = num3 % 3;
		System.out.println("나머지 연산 결과 : " + result2);
		
		String name = "김형찬";
		String job = "학생";
		System.out.println(name + job);
		System.out.println(404 + "강의실");
		
		int a = 4;
		String b = "층";
		String c = a + b;
		
		String d = a + ""; //"4"
		
		String e = "100"; //"100" >> 100
		int f = 50 + Integer.parseInt(e); // 문자 >> 숫자 메소드 기능 사용
		
	}

}
