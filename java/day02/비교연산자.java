package day02;

public class 비교연산자 {

	public static void main(String[] args) {
		
		// (>, <, >=, <=, ==, !=)
		int a = 10;
		int b = 5;
		boolean result = a > b; // 비교 연산의 결과는 boolean 타입
		System.out.println("a > b : " + (a > b));
		System.out.println("a < b : " + (a < b));
		System.out.println("a >= b : " + (a >= b));
		System.out.println("a <= b : " + (a <= b));
		System.out.println("a == b : " + (a == b));
		System.out.println("a != b : " + (a != b));

		
		String str1 = "F";
		String s = new String();
		String str2 = "D";
		//System.out.println(" str1 > str2 : " + (str1 > str2));
		System.out.println(" str1 == str2 : " + (str1 == str2));
		System.out.println(" str1 != str2 : " + (str1 != str2));
		
		// 문자열의 비교는 equals() 메소드 사용
		boolean result_str = str1.equals(str2); // true, false
		System.out.println (result_str);
		// 문자열의 크기(순서) 비교
		System.out.println(str1.compareTo(str2));
		
		
		
		
	}

}
