package day12_1;

public class 부모클래스 {
	
	static String staticVal = "부모클래스static";
	
	String parentStr;
	int parentInt;
	
	public String publicVal;
	protected String protectedVal;
	String defaultVal;
	private String privateVal;
	
	public void setparentStr(String parentStr) {
		this.parentStr = parentStr;
	}
	
	public String getprivateVal() {
		return privateVal;
	}
	
	public 부모클래스() {
		this.parentStr = "부모클래스str";
		this.parentInt = 100;
	}

}
