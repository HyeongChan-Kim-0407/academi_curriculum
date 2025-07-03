package day12;

public class Student {
	
	static String classNumber = "404호";
	
	private int number; 	// 번호
	private String name; 	// 이름
	
	static int count = 0; 	// 생성된 객체 수 
	
	public Student(int number, String name) {
		super();
		this.name = name;
		this.number = number;
		count++;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public static void staticMethod() {
		System.out.println("staticMethod() 호출");
	}
	
	public void Method() {
		System.out.println("voidMethod() 호출");
	}
	
	
	

}
