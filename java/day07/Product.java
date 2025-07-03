package day07;

public class Product {

	//상품이름 필드
	String proName;
	
	//상품가격 필드
	int proPrice;
	
	//상품종류 필드
	String proCat;
	
	public Product() {
		
	}
	
	public Product(String proName, int proPrice) {
		this.proName = proName;
		this.proPrice = proPrice;
	}
	
	public Product(String proName, int proPrice, String proCat) {
		this.proName = proName;
		this.proPrice = proPrice;
		this.proCat = proCat;
	}
	
}
