package jdbc_orders;

public class Orders {
	
	private int ocode;
	private Product product; 	// opcode
	private Member member; 		// omid
	private int ocount;
	private String odate;
	
	
	
	public Orders(int ocode, Product product, Member member, int ocount, String odate) {
		super();
		this.ocode = ocode;
		this.product = product;
		this.member = member;
		this.ocount = ocount;
		this.odate = odate;
	}
	public Orders(Product product, Member member, int ocount) {
		super();
		this.product = product;
		this.member = member;
		this.ocount = ocount;
	}
	public Orders() {
		// TODO Auto-generated constructor stub
	}
	public int getOcode() {
		return ocode;
	}
	public void setOcode(int ocode) {
		this.ocode = ocode;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getOcount() {
		return ocount;
	}
	public void setOcount(int ocount) {
		this.ocount = ocount;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	
}
