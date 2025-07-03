package day11;

import java.util.ArrayList;

public class ArrayList01 {

	public static void main(String[] args) {
//		ArrayList<타입> 객체이름 = new ArrayList<>();
		// ArrayList 객체 생성
		ArrayList<String> strList = new ArrayList<>();
		// 생성된 ArrayList에 값 저장 add();
		strList.add("문자0"); // 0번 인덱스에 저장
		strList.add("문자1"); // 1번 인덱스에 저장
		// ArrayList에 저장된 값 확인 get(인덱스번호);
		String str01 = strList.get(0); // 변수에 반환값 저장 후 활용 권장
		System.out.println(str01);
		strList.add("문자2"); // 2번 인덱스에 저장
		// ArrayList에 저장된 값의 개수 size()
		int size = strList.size();
		System.out.println(size);
		
		// 사용자 정의 클래스 타입의 객체를 저장
		// Student 클래스 타입의 객체들을 저장하는 ArrayList
		ArrayList<Student> stuList = new ArrayList<>();
		// Student 타입의 객체를 생성
		Student stu01 = new Student(1, "학생1");
		Student stu02 = new Student(2, "학생2");
		// stuList에 Student 타입 객체 저장
		stuList.add(stu01); // stu01객체를 0번 인덱스에 저장
		stuList.add(stu02); // stu02객체를 1번 인덱스에 저장
		// stuList에서 1번 인덱스에 저장된 객체 반환
		Student returnStu = stuList.get(1);
		System.out.println("이름 : " + returnStu.getName());
		System.out.println("번호 : " + returnStu.getNumber());
		
		System.out.println("저장된 학생 수 : " + stuList.size());
		
		
		
		
		
	}

}
