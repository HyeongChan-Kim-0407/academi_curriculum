package jdbc_orders;

public class Product {
	
	private int pcode;
	private String pname;
	private int pprice;
	private String pcompany;
	private String ptype;
	private int pstock;
	
	
	
	public Product() {
		super();
	}
	public Product(int pcode, String pname, int pprice, String pcompany, String ptype, int pstock) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.pprice = pprice;
		this.pcompany = pcompany;
		this.ptype = ptype;
		this.pstock = pstock;
	}
	
	public int getPcode() {
		return pcode;
	}
	public void setPcode(int pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public String getPcompany() {
		return pcompany;
	}
	public void setPcompany(String pcompany) {
		this.pcompany = pcompany;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public int getPstock() {
		return pstock;
	}
	public void setPstock(int pstock) {
		this.pstock = pstock;
	}
	
	
}
