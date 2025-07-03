package day01;

public class 연산자2 {

	public static void main(String[] args) {

		// 대입연산자 ( +=, -+, *=, 등등)
		int number = 1;
		// int number = 10; // 같은 변수명 선언 불가능
		int a = 1;
		System.out.println("a : " + a);
		a = 5;
		System.out.println("a : " + a);
		
		int b = 1;
		System.out.println("b : " + b);
		b += 5; // b = b + 5;
		System.out.println("b : " + b);
		
		// 증감연산자 ( ++, -- )
		int x = 10;
//		x++; // x = x + 1;
		System.out.println("x : " + x);
		int y = 20;
//		++y; // x = 1 + y;
		System.out.println("y : " + y);
		int z = x++ + ++y; // 11 + 22
		// 1. y값을 1증가 	(21 + 1 >> 22)
		// 2. z = x + y (22 + 11)
		// 3. x값을 1증가	(11 + 1 >> 12)
		System.out.println("z : " + z);
		System.out.println("x : " + x);
		System.out.println("y : " + y);		

	}

}
