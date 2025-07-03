package day12;

public class StaticMain3 {

	public static void main(String[] args) {
		
		Static01 st01 = new Static01();
		
		System.out.println(st01.name);
		System.out.println(Static01.strVal);
		System.out.println(Static01.intVal);
		
//		System.out.println("staticInfo() 호출");
//		Static01.staticInfo();
//		
//		System.out.println("showInfo() 호출");
//		st01.showInfo();
		
		int a = 10;
		int b = 5;
		int plusResult = Calculator.plus(a, b); // static은 공용으로 사용되는 기능 필요시 사용
		System.out.println(plusResult);
		
		int num = Integer.parseInt("120");
		
		

	}

}
