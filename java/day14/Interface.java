package day14;

public interface Interface { // 클래스의 메소드에 대한 설계도
	// 필드 선언시 인터페이스의 구현 클래스에선 static으로 사용
	// 추상메소드 (abstrict method) = 메소드의 실행부 명시 X 타입 이름(매개변수) 명시 O
	void printNumber(int num);
	void printString(String string);
	
}
