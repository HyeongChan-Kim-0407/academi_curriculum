package day12;

public class StaticMain {

	public static void main(String[] args) {
		
		Student stu1 = new Student(1, "학생1");
		Student stu2 = new Student(2, "학생2");
		// stu1, stu2 객체의 name 필드에 저장된 값 출력
		System.out.println("stu1.getName() : " + stu1.getName());
		System.out.println("stu2.getName() : " + stu2.getName());
		// stu1, stu2 객체의 classNumber 필드에 저장된 값 출력
		System.out.println("stu1.classNumber() : " + stu1.classNumber);
		System.out.println("stu2.classNumber() : " + stu2.classNumber);
		// stu1.classNumber를 501호로 변경
		stu1.classNumber = "501호";
		System.out.println("stu1의 classNumber를 501호로 변경 후");
		// stu1, stu2 객체의 classNumber 필드에 저장된 값 출력
		System.out.println("stu1.classNumber() : " + stu1.classNumber);
		System.out.println("stu2.classNumber() : " + stu2.classNumber);

	}

}
