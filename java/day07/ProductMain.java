package day07;

public class ProductMain {

	public static void main(String[] args) {
		//첫번째 상품 객체 > 기본생성자
		Product pro1 = new Product();
		pro1.proName = "샴푸";
		pro1.proPrice = 10000;
		pro1.proCat = "생활용품";
		
		System.out.println(pro1.proName);
		System.out.println(pro1.proPrice);
		System.out.println(pro1.proCat);
		
		//두번쨰 상품 객체 > 이름과 가격을 매개변수로 사용하는 생성자
		Product pro2 = new Product("라면", 5000);
		pro2.proCat = "음식";
		
		System.out.println(pro2.proName);
		System.out.println(pro2.proPrice);
		System.out.println(pro2.proCat);
		
		//세번쨰 상품 객체 > 모든 필드를 매개변수로 사용하는 생성자
		Product pro3 = new Product("프라이팬", 30000, "주방용품");
		
		System.out.println(pro3.proName);
		System.out.println(pro3.proPrice);
		System.out.println(pro3.proCat);
		
		

	}

}
