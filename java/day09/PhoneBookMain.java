package day09;

import java.util.Scanner;

public class PhoneBookMain {

	public static void main(String[] args) {
//		1. 사용자 입력을 활용해서 이름과 전화번호 입력받고 PhoneBook 객체에 저장
//		2. 생성된 PhoneBook 객체는 배열에 저장
//		3. 최소 3명분의 연락처 생성
//		4. [연락처 검색] 사용자 입력을 활용해서 이름을 입력하면 저장된 연락처에서 해당하는 전화번호 출력 

		Scanner scan = new Scanner(System.in);
		PhoneBook[] PB = new PhoneBook[3];
		PB[0] = new PhoneBook();
		PB[1] = new PhoneBook();
		PB[2] = new PhoneBook();

		System.out.println("첫 번째 사람의 이름과 전화번호를 입력해주십시오. ");
		PB[0].setPhoneBook(scan.next(), scan.next());

		System.out.println("두 번째 사람의 이름과 전화번호를 입력해주십시오. ");
		PB[1].setPhoneBook(scan.next(), scan.next());

		System.out.println("세 번째 사람의 이름과 전화번호를 입력해주십시오. ");
		PB[2].setPhoneBook(scan.next(), scan.next());

		System.out.println("[연락처 검색]");
		System.out.print("전화번호를 찾으려는 사람의 이름을 입력해주십시오. : ");
		String enterName = scan.next();

		boolean search = true;
		int i = 0;
		// 검색된 연락처가 없을 경우 >> boolean 혹은 int형 변수 추가하고 if문 하나 더 추가
		while (i < PB.length) {
			if (PB[i].getPhoneName().equals(enterName)) {
				System.out.println("검색된 전화번호 : " + PB[i].getPhoneNum());
				search = true;
				break;
			} else {
				i++;
				search = false;
				
			}
		}
		if (search == false) {
			System.out.println("검색된 연락처가 없습니다.");
		}

	}

}
