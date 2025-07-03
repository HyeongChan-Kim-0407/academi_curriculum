package day12;

public class Member { // static을 이용한 싱글톤 방식

	
	private static Member member;
	
	String mId;
	String mPw;

	private Member() {
		// 외부에서 new Member 생성자를 호출할 수 없도록 private으로 생성자를 선언
		
	}

	public static Member getInstance() {
		if (member == null) {
			member = new Member();
		}
		return member;
	}

}
