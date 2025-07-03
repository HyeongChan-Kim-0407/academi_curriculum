package member;

import java.util.ArrayList;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 회원정보를 저장할 ArrayList(회원목록) 객체 생성
		ArrayList<Member> memberList = new ArrayList<>();
		// 회원가입, 로그인 처리
		// 1. 회원가입
		// 1) 사용자로부터 회원정보를 입력받는다
//		System.out.print("가입할 아이디 : ");
//		String inputId = scan.next();
//		System.out.print("가입할 비밀번호 : ");
//		String inputPw = scan.next();
//		System.out.print("가입할 이름 : ");
//		String inputName = scan.next();
		// 2) 입력받은 회원정보를 회원목록(ArrayList)에 저장한다
		// Member 클래스 타입의 객체(mem01)에 입력받은 회원정보 저장
//		Member mem01 = new Member(inputId, inputPw, inputName);
		// 3) 아이디가 중복인 객체가 있는지 확인
		boolean overlap = true;
		Member memAdd = null;
		for(int idx = 0; idx < memberList.size(); idx++) {
			System.out.print("가입할 아이디 : ");
			String inputId = scan.next();
			System.out.print("가입할 비밀번호 : ");
			String inputPw = scan.next();
			System.out.print("가입할 이름 : ");
			String inputName = scan.next();
			
			Member mem = new Member(inputId, inputPw, inputName);
			Member check = memberList.get(idx);
				
			if(check.getmId().equals(mem.getmId())) {
				overlap = false;
			}else {
				memAdd = mem;
				break;
			}
			
		}
		// memberList에 mem01객체를 추가
		if(overlap) {
			
			memberList.add(memAdd);
			System.out.println("회원가입이 완료되었습니다.");
		}

		// 2. 가입된 회원정보로 로그인
		// 1) 사용자로부터 로그인할 회원정보 입력
		System.out.print("아이디 입력 : ");
		String loginId = scan.next();
		System.out.print("비밀번호 입력 : ");
		String loginPw = scan.next();

		// 2) 회원목록에 입력받은 회원정보와 일치하는 회원정보가 있는지 확인
		
		Member login = null; // 로그인 된 회원 정보를 저장할 객체

		for (int idx = 0; idx < memberList.size(); idx++) {
			Member mem = memberList.get(idx);
			if (mem.getmId().equals(loginId) && mem.getmPw().equals(loginPw)) {
				login = mem;
				break;
			}
//			if((memberList.get(idx).getmId().equals(loginId)) && (memberList.get(idx).getmPw().equals(loginPw)) ) {
//				System.out.println("로그인 완료");
//			}else if(!(memberList.get(idx).getmId().equals(loginId)) && (memberList.get(idx).getmPw().equals(loginPw)) ) {
//				System.out.println("아이디를 확인해주십시오");
//			}else if((memberList.get(idx).getmId().equals(loginId)) && !(memberList.get(idx).getmPw().equals(loginPw)) ) {
//				System.out.println("비밀번호를 확인해주십시오");
//			}else {
//				System.out.println("일치하는 회원정보가 없습니다.");
//			}	
		}

		// 2-3 일치하는 회원정보를 찾았을 경우에는 회원의 이름을 출력
		if (login != null) {
			System.out.println(login.getmName());
		} else {// 찾지 못 한 경우에는 아이디 또는 비밀번호가 일치하지 않습니다 출력
			System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
	}

}
