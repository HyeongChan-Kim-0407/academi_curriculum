package day03;

public class for3 {

	public static void main(String[] args) {
		// break;		즉시 종료
		// continue;	건너뛰기
		for (int i = 1; i <= 10; i++) {
			System.out.println(i);
			if(i == 5) {
				break;
			}
		}
		
		for (int i = 1; i <= 10; i++) {
			if(i == 5) {
				continue;
			}
			System.out.println(i);
		}
		
		for (int i = 1 ; i <= 10; i++) {
			if ((i % 3) == 0) {
				System.out.println(i);
			} else {
				continue;
			}
			
		}
		
	}

}
