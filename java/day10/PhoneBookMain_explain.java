package day10;

import java.util.Scanner;

public class PhoneBookMain_explain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PhoneBook_explain[] pbList = new PhoneBook_explain[3];
		
		// 1. 연락처 생성(PhoneBook 객체)
		String name = "자바"; // scan.next();
		String phoneNumber = "010-1111-1111"; // scan.next();
		PhoneBook_explain pb1 = new PhoneBook_explain();
		pb1.setName(name);
		pb1.setPhoneNumber(phoneNumber);
		pbList[0] = pb1;
		
		// 2. 연락처 생성(PhoneBook 객체)
		String name2 = "오라클"; // scan.next();
		String phoneNumber2 = "010-2222-2222"; // scan.next();
		PhoneBook_explain pb2 = new PhoneBook_explain(name2, phoneNumber2);
		pbList[1] = pb2;
		
		// 3. 연락처 생성
		pbList[2] = new PhoneBook_explain("파이썬", "010-3333-3333");
		
		// 전체 연락처(이름, 전화번호)를 출력
		for(int idx = 0; idx < pbList.length; idx++) {
//			pbList[idx].showInfo();
			System.out.println(pbList[idx]);
		}
		
		System.out.print("검색할 이름>>");
		String enterName = scan.next();
		boolean searchResult = true;
		String searchPb = null;
		
		for(int i = 0; i < pbList.length; i++) {
			if(pbList[i].getName().equals(enterName)) {
				searchPb = pbList[i].getPhoneNumber();
				searchResult = true;  
				break;
			}else {
				searchResult = false;
			}
		}
		if (searchResult == false) {
			System.out.println("찾으시는 연락처가 없습니다.");
		}else {
			System.out.println("찾으시는 번호 : " + searchPb);
		}
	}

}
