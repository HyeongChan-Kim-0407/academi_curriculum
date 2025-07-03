package day12;

public class Static01 {
	
	static String strVal;
	static int intVal;
	
	String name;
	int number;
	
	public void showInfo() {
		System.out.println("name : " + name);
		System.out.println("number : " + number);
		System.out.println("strVal : " + strVal);
		System.out.println("intVal : " + intVal);
	}

	public static void staticInfo() {
		System.out.println("staticInfo() 호출");
		System.out.println("strVal : " + strVal);
		System.out.println("intVal : " + intVal);
	}
	
	
	
	
	
	
	
	
	
}
