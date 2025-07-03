package day15;

public class CardPayment implements Payment {
	
	
	public void pay(int price) {
		
		System.out.println( price + "원 카드 결제됐습니다.");
		
	}
	
	static String str = "스태틱_카드결제";
	
}
