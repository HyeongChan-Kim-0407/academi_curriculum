package day12;

import day12_1.부모클래스;

public class Child1 extends 부모클래스{
	
	String childProtectedVal;
	
	public void ChildInfo() { // 다른 패키지에선 public만 사용가능 하지만, 상속을 받았다면 protected까지 사용가능
		System.out.println("publicVal : " + publicVal);
//		System.out.println("privateVal : " + privateVal);
//		System.out.println("defaultVal : " + defaultVal);
		System.out.println("protectedVal : " + protectedVal);
		childProtectedVal = protectedVal;
	}

}
