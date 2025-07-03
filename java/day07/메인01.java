package day07;

public class 메인01 {
	
	public static void main(String[] args) {
		// 클래스 01에 정의한 drive(), stop() 메소드를 호출
		// 1. 클래스 01에 대한 객체(car01) 생성
		// 클래스명 객체명 = new 생성자();
		클래스01 car01 = new 클래스01();
			
		// 2. 객체(car01)를 활용해서 메소드(drive, stop)를 호출
		car01.drive();
		car01.stop();
		
		// 클래스 01에 대한 객체(car02) 생성
		클래스01 car02 = new 클래스01();
		
		// car01의 속도를 출력
		System.out.println( "car01.speed : " + car01.speed);
		System.out.println( "car02.speed : " + car02.speed);
		// 2. 객체에 필드 값 저장
		
		
		// car01의 speed를 100을 저장
		car01.speed = 100;
		System.out.println( "car01.speed : " + car01.speed);
		System.out.println( "car02.speed : " + car02.speed);
		
		
		
	}
	
}
