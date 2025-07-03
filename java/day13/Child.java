package day13;

public class Child extends Parent {
	
	// 자식클래스의 생성자 호출시 부모클래스의 생성자 호출 선행 >> super();
	public Child() {
		System.out.println("Child() 호출");
	}
	
	// 메소드 재정의(오버라이드)
	@Override // 부모클래스에 있는 메소드와 이름, 매개변수등 형태가 같다
	public void showInfo() {
		System.out.println("자식클래스의 showInfo() 호출");
	}
	// 오버로드 = 같은 이름의 메소드를 매개변수의 형태 혹은 갯수에 변화를 줘서 정의하는 것
	
	
}
