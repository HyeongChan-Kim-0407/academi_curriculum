package day12_1;

public class 실행클래스 {

	public static void main(String[] args) {
		부모클래스 pa1 = new 부모클래스();
		pa1.setparentStr("부모클래스STR");
		System.out.println(pa1.parentStr);
		System.out.println(pa1.parentInt);
		System.out.println(pa1.publicVal);
		System.out.println(pa1.protectedVal);
		System.out.println(pa1.defaultVal);
//		System.out.println(pa1.privateVal);
//		부모클래스.staticVal = "부모클래스";
		
		자식클래스 ch1 = new 자식클래스();
		System.out.println(ch1.parentStr);
		System.out.println(ch1.parentInt);
//		System.out.println(ch1.privateVal()); // private은 상속 불가

//		자식클래스.staticVal = "자식클래스";
		
		System.out.println(부모클래스.staticVal);
		System.out.println(자식클래스.staticVal);
		

	}

}
