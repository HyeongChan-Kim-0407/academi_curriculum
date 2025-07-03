package day02;

public class 논리연산자 {

	public static void main(String[] args) {
		
		// ( && and , || or )
		boolean isAdult = true;
		boolean ticket = false;
		System.out.println(isAdult && ticket); // false
		
		System.out.println(isAdult || ticket); // true

		int age = 30;		//나이
		int money = 5000;	//돈, 티켓 10000원
		System.out.println((age > 20) && (money > 10000) );
		
	}

}
