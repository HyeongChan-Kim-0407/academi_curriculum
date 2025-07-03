package day10_phoneBook;

public class PhoneBook { // V.O Value Object,  D.T.O = Data Transfer Object // Model
	// M.V.C Model = 데이터 View = 뷰어, 사용자 Controller = 중간다리

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
	
	public void setPhoneBook(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	
	public PhoneBook() {
		
	}
	
	public PhoneBook(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// 이름과 전화번호를 출력하는 메소드
	public void showInfo() {
		System.out.println("[이름] : " + name + " [전화번호] : " + phoneNumber);
	}
	
	@Override
	public String toString() {
		return "[이름] " + name + " , [전화번호] " + phoneNumber ;
	}
}
