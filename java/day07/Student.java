package day07;

public class Student {
	// 학생 번호를 저장할 필드
	int studentNumber;
	// 학생 이름을 저장할 필드
	String studentName;
	// 전공을 저장할 필드
	String major = "정보통신";
	
	
	
	// 기본생성자
	public Student() {
		super();
	}
	
	// 매개변수가 있는 생성자들 (생성자 오버로딩)
	public Student(int studentNumber) {
		super();
		this.studentNumber = studentNumber;
	}

	public Student(String studentName) {
		super();
		this.studentName = studentName;
	}

	public Student(int studentNumber, String studentName) {
		super();
		this.studentNumber = studentNumber;
		this.studentName = studentName;
	}
	
	
	
	
}
