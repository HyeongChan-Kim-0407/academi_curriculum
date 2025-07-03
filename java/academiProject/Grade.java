package academiProject;

public class Grade {
	
	private String Grade;
	private int Purprice;
	private int Discount;
	
	public Grade(String grade, int purprice, int discount) {
		super();
		Grade = grade;
		Purprice = purprice;
		Discount = discount;
	}
	public String getGrade() {
		return Grade;
	}
	public void setGrade(String grade) {
		Grade = grade;
	}
	public int getPurprice() {
		return Purprice;
	}
	public void setPurprice(int purprice) {
		Purprice = purprice;
	}
	public int getDiscount() {
		return Discount;
	}
	public void setDiscount(int discount) {
		Discount = discount;
	}
	
}
