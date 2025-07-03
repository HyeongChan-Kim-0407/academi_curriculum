package academiProject;

public class Product {

	private int pcode;
	private String pname;
	private String ptype;
	private String pcategory;
	private int pprice;
	private int pstock;

	public Product(int pcode, String pname, String ptype, String pcategory, int pprice, int pstock) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.ptype = ptype;
		this.pcategory = pcategory;
		this.pprice = pprice;
		this.pstock = pstock;
	}

	public Product(String pname, String ptype, String pcategory, int pprice, int pstock) {
		super();
		this.pname = pname;
		this.ptype = ptype;
		this.pcategory = pcategory;
		this.pprice = pprice;
		this.pstock = pstock;
	}

	/*
	 * public Product(String pname, String ptype, String pcategory) { super();
	 * this.pname = pname; this.ptype = ptype; this.pcategory = pcategory; }
	 */

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

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPcategory() {
		return pcategory;
	}

	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public int getPstock() {
		return pstock;
	}

	public void setPstock(int pstock) {
		this.pstock = pstock;
	}

	@Override
	public String toString() {
		return String.format("ID: %d | 상품명: %s | 종류: %s | 카테고리: %s | 가격: %d | 재고: %d", pcode, pname, ptype, pcategory,
				pprice, pstock);
	}

}
