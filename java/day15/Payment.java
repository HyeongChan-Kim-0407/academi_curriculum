package day15;

// 결제 기능 명시
public interface Payment {
	
	int NUMBER = 100; // 인터페이스에 필드 생성 시 final 타입의 static 필드로 생성됨 : 상수 / static final 생성시에는 이름을 대문자로 표기
	void pay(int price);
	
}
