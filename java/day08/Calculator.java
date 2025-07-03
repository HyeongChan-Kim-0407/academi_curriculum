package day08;

public class Calculator {
	// 메소드
//	(접근제한자) 타입 메소드명( (매개변수) ) {
//		메소드가 호출되면 실행되는 코드
//	}
	
	// 덧셈기능 메소드
	
	int plus(int num1, int num2) {
		// 두 숫자의 합연산
		int result = num1 + num2;
		// 연산 결과를 반환
		return result;
	}
	
	double divide(int num1, int num2) {
		return (double)num1 / num2;
	}
	
	
	
	
	
	
	
	
	
	// 리턴값이 없는 메소드 void 타입
	void voidMethod() {
		System.out.println("voidMethod() 호출");
	}
	// 리턴값이 있는 메소드 < 리턴 타입을 클래스로 작성도 가능
	// 메소드의 매개변수 타입은 리턴 타입과 관계 X
	int intMethod() {
		System.out.println("intMethod() 호출");
		return 100;//(리턴타입에 맞는) 반환값
	}
	
	String strMethod() {
		System.out.println("strMethod() 호출");
		return "문자열";//(리턴타입에 맞는) 반환값
	}
	
	Boolean boolMethod() {
		System.out.println("boolMethod() 호출");
		boolean result = true;
		return result; //변수를 반환하는 것도 가능
	}
	
	
	
	
}
