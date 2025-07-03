package day09;

public class PhoneBook {
//	1. 이름과 전화번호 저장 가능
//	2. 이름과 전화번호가 저장된 객체는 배열에서 관리
//	3. 이름을 입력해서 전화번호를 검색할 수 있도록 한다.
	
	private String phoneName;
	private String phoneNum;
	
	public void setPhoneBook(String phoneName, String phoneNum) {
		this.phoneName = phoneName;
		this.phoneNum = phoneNum;
	}
	
	public String getPhoneName () {
		return phoneName;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	

}
