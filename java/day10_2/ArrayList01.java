package day10_2;

import java.util.ArrayList;

import day10_phoneBook.PhoneBook;

public class ArrayList01 {

	public static void main(String[] args) {
		// List = 하나의 객체에 여러가지 데이터를 담을 수 있다는 개념, 인터페이스.
		// Array = 배열의 형식
		// ArrayList = 하나의 객체에 여러가지 데이터를 담을건데 그걸 배열의 형식을 따라서 담겠다.
		
		//ArrayList<타입> 이름 = new ArrayList<>(생성자);
		
		String[] strArr = new String[5];
		strArr[0] = "java";
		strArr[1] = "oracle";
		System.out.println(strArr[0] + "\n");
		System.out.println(strArr.length);
		
		for(int idx = 0 ; idx < strArr.length; idx++) {
			System.out.println(strArr[idx]);
		}
		
		
		ArrayList<String> strList = new ArrayList<>();
		// ArrayList 객체에 값 저장 add();
		strList.add("JAVA"); // strList의 0번 인덱스에 "JAVA" 저장
		strList.add("ORACLE"); // strList의 1번 인덱스에 "ORACLE" 저장
		System.out.println(strList.get(0));
		System.out.println(strList.get(1));
//		System.out.println(strList.get(2));
		
		strList.add(1, "SCRIPT");
		System.out.println("add 1 실행 후");
		System.out.println(strList.get(1));
		System.out.println(strList.get(2));
		System.out.println(strList.size());
		
		for(int idx = 0; idx <strList.size(); idx++) {
			System.out.println(strList.get(idx));
		}
		
		//remove
		
		System.out.println("remove(0) 실행 후");
		strList.remove(0);
		
		for(int idx = 0; idx <strList.size(); idx++) {
			System.out.println(strList.get(idx));
		}
		
		strList.remove("SCRIPT"); // 기본형만 가능 참조형 X
		System.out.println("remove SCREIPT 실행 후");
		for(int idx = 0; idx < strList.size(); idx++) {
			System.out.println(strList.get(idx));
		}
		
		PhoneBook pb1 = new PhoneBook("자바", "010-1111-1111");
		PhoneBook pb2 = new PhoneBook("오라클", "010-2222-2222");
		
		ArrayList<PhoneBook> pbList = new ArrayList<>();
		pbList.add(pb1);
		pbList.add(0, pb2); // 0번 인덱스에 오라클 입력
		
		for(int idx = 0; idx < pbList.size(); idx++) {
			System.out.println(pbList.get(idx));
		}
		
		
	}

}
