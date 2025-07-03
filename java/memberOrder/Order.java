package memberOrder;

public class Order {
	
	//회원정보
	private Member member;
	//상품정보
	private Product product;
	//주문정보
	private int count;
	
	
	
	public Order(Member member, Product product, int count) {
		super();
		this.member = member;
		this.product = product;
		this.count = count;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
