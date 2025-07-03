package day08;

public class Car {
	// 필드, 메소드, 생성자
	// 접근제한자 ( public, private, protected, default )
	// public : 모든 접근이 가능
	// private : 클래스 내부에서만 접근이 가능
	// default : 같은 패키지 내에서만 접근이 가능
	// protected : default와 같지만, 상속 관계일 경우 다른 패키지에서도 접근이 가능
	
	public int speed;
	String color;
	
	private String brand;
	
	
	public void drive() {
		System.out.println("출발합니다.");
	}
	
	public void stop() {
		System.out.println("정지합니다.");
	}
	
	public Car() {
		
	}
	
	public Car(String brand) {
		this.brand = brand;
	}
	
	
	
	
}
