package day10;

public class PhoneBook_explain {

	private String name;
	private String phoneNumber;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public PhoneBook_explain() {
		
	}
	
	public PhoneBook_explain(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// 이름과 전화번호를 출력하는 메소드
	public void showInfo() {
		System.out.println("이름" + name + " [전화번호]" + phoneNumber);
	}
	
	@Override
	public String toString() {
		return "[이름] " + name + ", [전화번호] " + phoneNumber ;
	}
	
	
	
	
	
	
	
}
