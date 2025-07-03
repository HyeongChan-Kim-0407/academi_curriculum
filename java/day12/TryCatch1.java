package day12;

public class TryCatch1 {

	public static void main(String[] args) {
		//예외처리 try-catch
/*		
		try {
			// 예외가 발생할 수 있는 코드
		} catch(예외타입 변수이름) {
			// 예외가 발생했을 때 실행할 코드 
		} catch(예외타입 변수이름) {
			
		}
*/
		
		try {
//			int result = 10 / 0;
//			System.out.println("result : " + result);
			
			String str = "A";
			int num = Integer.parseInt(str); // 문자를 숫자로 형변환
			System.out.println("number : " + num);
			
			
			
		} catch(ArithmeticException ari) {
			System.out.println("숫자는 0으로 나눌 수 없습니다.");
		} catch(NumberFormatException num) {
			System.out.println("숫자로 형변환될 수 없는 문자입니다.");
		}
		
		System.out.println("try-catch 이후 실행");
		
		try {
//			int result = 10 / 0;
//			System.out.println("result : " + result);
			
			String str = "A";
			int num = Integer.parseInt(str); // 문자를 숫자로 형변환
			System.out.println("number : " + num);
			
			
			
		} catch (ArithmeticException Ari) {
			System.out.println("숫자는 0으로 나눌 수 없습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외발생");
		}
		System.out.println("try-catch 이후 실행");
		
		
		
	}

}
