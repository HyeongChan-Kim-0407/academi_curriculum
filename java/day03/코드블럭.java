package day03;

public class 코드블럭 {

	public static void main(String[] args) {
		int a = 10; //전역변수
		if (a == 10) {
			int b = 20; //지역변수
			System.out.println(a + b);
		} else if(a == 20) {
			int b = 40;
			System.out.println(a + b);
		}
		
		switch(a) {
			case 10:
				int c = 20;
				break;
			case 20:
				c = 30;
				break;
			default:
		
		}

	}

}
