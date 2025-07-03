package day10_phoneBook;

import java.util.Scanner;


public class PhoneBookService { // Controller
	Scanner scan;
	// 연락처 시스템 기능 정의
	private PhoneBook[] pbList;
	private int count; // 저장된 연락처의 수 저장

	public PhoneBookService() {
		this.pbList = new PhoneBook[10];
		pbList[0] = new PhoneBook("테스트", "010-1111-1111");
		count++; // 새 연락처가 저장되면 1 증가
		scan = new Scanner(System.in);
	}

	// 메뉴 출력 기능
	public void showMenu() {
		System.out.println("\n===[메뉴 선택]===\n");
		System.out.println("[1]목록 [2]저장 [3]조회 [4]수정 [5]삭제 [0]종료");
		System.out.print("메뉴 선택 : ");
	}

	// 1. 연락처 목록 기능
	public void showAllList() { // 전체 연락처 출력 기능
		for (int idx = 0; idx < pbList.length; idx++) {
			if (pbList[idx] != null) {
				pbList[idx].showInfo();
			}
		}
	}

	// 2. 연락처 저장 기능

	public void saveList() {

		// 사용자로부터 이름, 전화번호를 입력받는다.
		System.out.println(" [ 새로운 연락처 저장 ] ");
		System.out.print("이름 : ");
		String enterName = scan.next();
		System.out.print("번호 : ");
		String enterNum = scan.next();

		// PhoneBook 객체에 저장
		PhoneBook pb = new PhoneBook();
		pb.setName(enterName);
		pb.setPhoneNumber(enterNum);

		// pbList 배열에 저장
		if (count == pbList.length) {
			count = 0;
		}
		pbList[count] = pb; // 인덱스 : 0 ~ (배열의 크기 - 1)
		count++;
//		for ( int idx = 0; idx < pbList.length; idx++ ) {
//			if(pbList[idx] == null) {
//				pbList[idx] = pb;
//				count++;
//				break;
//			}
//		}

	}

	// 3. 연락처 조회 기능

	public void searchList() {
		// 이름으로 번호 조회 기능
		// 1) 조회할 이름 입력
		// 2) 연락처 목록에서 같은 이름이 저장된 객체 검색
		// 3) 조회된 객체로부터 전화번호 출력
		// 4) 목록에서 조회되지 않는 경우 실패 메시지 출력

		System.out.println(" [ 연락처 조회 ] ");
		// 1.연락처 목록에서 같은 이름이 저장된 인덱스 번호 조회
		int index = searchIndexByName();

		if (index >= 0) {
			pbList[index].showInfo();
		} else {
			System.out.println("찾으시는 연락처가 없습니다.");
		}

	}

	// 4. 연락처 수정 기능
	// 1) 수정할 이름 입력
	// 2) 연락처 목록에서 같은 이름이 저장된 객체 조회
	// 3) 조회된 객체의 전화번호를 새로운 전화번호 변경
	// 4)

	public void modifyList() {

		System.out.println(" [ 연락처 수정 ] ");

		int index = searchIndexByName();

		if (index < 0) {
			System.out.println("연락처를 찾을 수 없습니다.");
			return;
		}

		pbList[index].showInfo();
		System.out.println("새로운 전화번호를 입력해주십시오. : ");
		String enterNum = scan.next();
		pbList[index].setPhoneNumber(enterNum);
		System.out.println("전화번호가 수정되었습니다.");

	}

	// 5. 연락처 삭제 기능
	// 1) 삭제할 이름 입력
	// 2) 연락처 목록에서 같은 이름이 저장된 객체 조회
	// 3) 조회된 객체를 연락처 목록에서 삭제

	public void removeList() {

		System.out.println(" [ 연락처 삭제 ] ");

		int index = searchIndexByName();
		
		if(index < 0) {
			System.out.println("찾으시는 연락처가 없습니다");
			return;
		}
		
		pbList[index].showInfo();
		System.out.println("삭제하시겠습니까? [1] 예 [2] 아니오");
		int enterNum = scan.nextInt();
		switch (enterNum){
			case 1:
				pbList[index].setPhoneBook(null, null);
				System.out.println("연락처가 삭제됐습니다.");
				break;
			case 2:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
		}
		
	}

	// 인덱스 번호를 찾는 기능
	private int searchIndexByName() {
		System.out.print("이름 입력 : ");
		String enterName = scan.next();
		int index = -1; // 인덱스 번호를 저장할 변수
		for (int idx = 0; idx < pbList.length; idx++) {
			if (pbList[idx] != null) {
				String pbName = pbList[idx].getName();
				if (pbName.equals(enterName)) {
					index = idx;
					break;
				}

			}

		}

		return index;
	}

}
