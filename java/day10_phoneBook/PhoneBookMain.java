package day10_phoneBook;

import java.util.Scanner;

public class PhoneBookMain { // View

	public static void main(String[] args) {
		//PhoneBookService 객체 생성
		PhoneBookService pbsvc = new PhoneBookService();
		//Scanner scan = new Scanner(System.in);
		
		
		boolean run = true;
		while(run) {
			pbsvc.showMenu(); // 메뉴 출력 기능 호출
			int selectMenu = pbsvc.scan.nextInt(); // 사용자 > 메뉴 선택
			
			switch (selectMenu) {
			case 1: // 연락처 목록 기능 호출
				pbsvc.showAllList();
				break;
			case 2: // 연락처 저장 기능 호출
				pbsvc.saveList();
				break;
			case 3: // 연락처 조회 기능 호출
				pbsvc.searchList();
				break;
			case 4: // 연락처 수정 기능 호출
				pbsvc.modifyList();
				break;
			case 5: // 연락처 삭제 기능 호출
				pbsvc.removeList();
				break;
			case 0: // 종료 메뉴 선택
				run = false;
				break;
			default:
					System.out.println("잘못 입력하셨습니다.");
			}
			// 선택된 기능 호출
			
			// 
			
			
			
			
		}
		System.out.println("시스템 종료");
	}

}
