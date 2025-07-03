package day07;

public class StudentMain {

	public static void main(String[] args) {
		
		// Student타입의 객체 생성
		Student student01 = new Student();
		
		// 번호와 이름 저장
//		student01.studentNumber = 202008013;
		student01.studentName = "김형찬";
		
		// 번호와 이름을 출력
		System.out.println(student01.studentNumber);
		System.out.println(student01.studentName);
		System.out.println("student01.major : " + student01.major);
		
		// 학생 번호를 매개변수로 사용하는 생성자로 객체 생성
		Student student02 = new Student( 2 );
		
		System.out.println( "student02.studentNumber : " + student02.studentNumber );
		System.out.println( "student02.studentName : " + student02.studentName );
		System.out.println("student02.major : " + student02.major);
		
		// 학생 이름을 매개변수로 사용하는 생성자로 객체 생성
		Student student03 = new Student("김자바");
		
		System.out.println( "student03.studentNumber : " + student03.studentNumber );
		System.out.println( "student03.studentName : " + student03.studentName );
		System.out.println("student03.major : " + student03.major);
		
		// 번호 4, 이름 오라클
		Student stu04 = new Student( 4, "오라클" );
		
		System.out.println("stu04.studentNumber : " + stu04.studentNumber);
		System.out.println("stu04.studentName : " + stu04.studentName);
		System.out.println("stu04.major : " + stu04.major);

	}

}
