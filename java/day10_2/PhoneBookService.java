package day10_2;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBookService {
	// 스캐너
	Scanner scan;
	// 연락처를 저장할 ArrayList
	private ArrayList<PhoneBook> pbList;

	public PhoneBookService() { // 기본생성자
		scan = new Scanner(System.in);
		pbList = new ArrayList<>();
	}

	public void showAllList() {
		if (pbList.size() == 0) {
			System.out.println("등록된 연락처가 없습니다.");
			return;
		}

		for (int idx = 0; idx < pbList.size(); idx++) {
			System.out.println("[이름] " + pbList.get(idx).getName() + "[번호] " + pbList.get(idx).getPhoneNumber());
		}

	}

	public void addPbList() {

		// 중복된 전화번호를 저장되지 않도록 함.

		System.out.print(" [ 새 연락처 저장 ] ");
		System.out.print("이름 입력 : ");
		String inputName = scan.next();
		System.out.print("번호 입력 : ");
		String inputNumber = scan.next();
		String reInputNumber = null;
		boolean check = false;
		for (int idx = 0; idx < pbList.size(); idx++) {
			if (inputNumber.equals(pbList.get(idx).getPhoneNumber())) {
				System.out.println("동일한 번호가 이미 존재합니다. 다시 입력해주십시오.");
				reInputNumber = scan.next();
				for (int re = idx; re < pbList.size(); re++) {
					if (reInputNumber.equals(pbList.get(re).getPhoneNumber())) {
						idx--;
						break;
					} else {
						check = true;
						continue;
					}
					
				}
			} 

		}
		if(check == true) {
			pbList.add(new PhoneBook(inputName, reInputNumber));
			return;
		}
		pbList.add(new PhoneBook(inputName, inputNumber));
//		PhoneBook pb = new PhoneBook(inputName, inputNumber);
//		pbList.add(pb);

	}

	public void searchPbList() {
		// 1. 조회할 연락처의 인덱스 번호 검색
		int index = searchIndexByName();
		if (index < 0) {
			System.out.println("연락처를 찾을 수 없습니다.");
			return;
		}

		System.out.println("[번호] " + pbList.get(index).getPhoneNumber());

	}

	public void modifyPbList() {

		System.out.println(" [ 연락처 수정 ] ");

		int index = searchIndexByName();
		if (index < 0) {
			System.out.println("연락처를 찾을 수 없습니다.");
			return;
		}
		System.out.println("검색된 연락처 : " + pbList.get(index).getPhoneNumber());
		System.out.print("새 전화번호를 입력해주십시오 : ");
		String newNumber = scan.next();
		pbList.get(index).setPhoneNumber(newNumber);
		System.out.println("전화번호가 수정되었습니다.");

	}

	public void removePbList() {

		System.out.println(" [ 연락처 삭제 ] ");

		int index = searchIndexByName();

		if (index < 0) {
			System.out.println("찾으시는 연락처가 없습니다");
			return;
		}

		System.out.println("[이름] " + pbList.get(index).getName() + "[번호] " + pbList.get(index).getPhoneNumber());
		System.out.println("삭제하시겠습니까? [1] 예 [2] 아니오");
		int enterNum = scan.nextInt();
		switch (enterNum) {
		case 1:
			pbList.remove(index);
			System.out.println("연락처가 삭제됐습니다.");
			break;
		case 2:
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}

	}

	// 연락처가 저장된 인덱스를 이름으로 조회하는 메소드
	private int searchIndexByName() {
		System.out.print("조회할 이름 입력: ");
		String inputName = scan.next();
		int index = -1; // 검색된 인덱스를 저장할 변수
		for (int idx = 0; idx < pbList.size(); idx++) {
			if (pbList.get(idx).getName().equals(inputName)) {
				index = idx;
				continue;
			}
		}
		return index;
	}

}
