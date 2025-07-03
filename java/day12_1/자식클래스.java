package day12_1;

public class 자식클래스 extends 부모클래스 {
	
	static String staticVal = "자식클래스static";
	
	String childVal;
	String defaultVal; // 상속받은 필드를 재정의 가능

	public void ChildInfo() { // 같은 패키지에서 상속을 받았다면 private을 제외하곤 전부 사용가능
		System.out.println("publicVal : " + publicVal);
//		System.out.println("privateVal : " + privateVal);
		System.out.println("defaultVal : " + defaultVal);
		System.out.println("protectedVal : " + protectedVal);
	}
	
	

}
