package day03;

public class Switch1 {

	public static void main(String[] args) {
		// 조건문 - switch
		/*switch(조건변수) {
			case 값1:
				// 조건변수가 값1일 때 실행
				break;
			case 값2:
				// 조건변수가 값2일 때 실행
				break;
			default:
				// 조건변수가 어떤 case에도 해당되지 않을 때 실행
		}
		*/
		
		int menuNumber = 0;
		switch(menuNumber) {
			case 1:
				System.out.println("menuNumber가 1일 때 실행");
				break;
			case 2:
				System.out.println("menuNumber가 2일 때 실행");
				break;
			default:
				System.out.println("해당되는 case가 없을 때 실행");
		}
		
		String today = "금요일";
		switch(today) {
			case "금요일":
				System.out.println("내일은 토요일입니다.");
				break;
			default:
				System.out.println("해당되는 case가 없을 때 실행");
		}
		
		
		
		
		

	}

}









