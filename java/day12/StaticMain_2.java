package day12;

public class StaticMain_2 {

	public static void main(String[] args) {
		//객체 생성
		Student stu1 = new Student(1, "학생1");
		System.out.println("학생 수 : " + Student.count);
		// classNumber 필드의 값 출력
		System.out.println( Student.classNumber );
		
		Student.classNumber = "505호";
		
		System.out.println( Student.classNumber );
		
		Student.staticMethod();
		
		Student stu2 = new Student(2, "학생2");
		System.out.println("학생 수 : " + Student.count);
		
		// Member타입의 객체 생성
		Member member1 = Member.getInstance();
		member1.mId = "id01";
		member1.mPw = "1234";
		
		Member member2 = Member.getInstance();
		System.out.println(member2.mId);
		member2.mId = "id02";
		member2.mPw = "5678";
		System.out.println(member1.mId);
		

	}

}
