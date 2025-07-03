package academiProject;

public class Orders {
	
	private int ocode;
	private Product product;
	private Member member;
	private int ocount;
	private String orderdate;
	private String purdate;
	private String purway;
	private String orderstate;
	
	public Orders(int ocode, Product product, Member member, int ocount, String orderdate, String purdate,
			String purway, String orderstate) {
		super();
		this.ocode = ocode;
		this.product = product;
		this.member = member;
		this.ocount = ocount;
		this.orderdate = orderdate;
		this.purdate = purdate;
		this.purway = purway;
		this.orderstate = orderstate;
	}
	
	public Orders(Product selPd, Member loginMember, int inputCount) {
		this.product = selPd;
		this.member = loginMember;
		this.ocount = inputCount;
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
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getPurdate() {
		return purdate;
	}
	public void setPurdate(String purdate) {
		this.purdate = purdate;
	}
	public String getPurway() {
		return purway;
	}
	public void setPurway(String purway) {
		this.purway = purway;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
}
