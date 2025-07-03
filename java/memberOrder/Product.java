package memberOrder;

public class Product {
	
	private String pName;	//상품명
	private int price;	//가격
	
	public Product(String pName, int price) {
		super();
		this.pName = pName;
		this.price = price;
	}
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
