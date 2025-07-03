package day10_2;

public class PhoneBookMain {

	public static void main(String[] args) {
		
		PhoneBookService pbsvc = new PhoneBookService();
		
		boolean run = true;
		
		while(run) {
			// 메뉴 출력 기능 호출
			System.out.println("\n===[메뉴 선택]===");
			System.out.println("[1]목록 [2]저장 [3]조회 [4]수정 [5]삭제 [0]종료 ");
			System.out.print("메뉴 선택 : ");
			int selectMenu = pbsvc.scan.nextInt();
			
			switch(selectMenu) {
			case 1: // 전체 연락처 출력 기능 호출
				pbsvc.showAllList();
				break;
			case 2: // 새 연락처 저장 기능 호출
				pbsvc.addPbList();
				break;
			case 3: // 연락처 조회 기능 호출
				pbsvc.searchPbList();
				break;
			case 4: // 연락처 수정 기능 호출
				pbsvc.modifyPbList();
				break;
			case 5: // 연락처 삭제 기능 호출
				pbsvc.removePbList();
				break;
			case 0: 
				run = false;
			default:
				System.out.println("잘못 선택하셨습니다.");
			}
			
		}
		System.out.println("프로그램 종료");
	}

}
