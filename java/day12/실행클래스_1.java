package day12;

import day12_1.부모클래스;

public class 실행클래스_1 {

	public static void main(String[] args) {
		
		부모클래스 pa1 = new 부모클래스();
//		System.out.println(pa1.parentStr);
//		System.out.println(pa1.parentInt);
		System.out.println(pa1.publicVal);
//		System.out.println(pa1.protectedVal);
//		System.out.println(pa1.defaultVal);
//		System.out.println(pa1.privateVal);
		
		Child1 ch1 = new Child1();
		System.out.println(pa1.publicVal);
		System.out.println(ch1.childProtectedVal);
		
	}

}
