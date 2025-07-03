package day14;

import java.util.Scanner;

public class Implement_Class2 implements Interface {
	
	public void printNumber(int inputNum) {
		Scanner scan = new Scanner(System.in);
		System.out.print("숫자 입력 : ");
		inputNum = scan.nextInt();
		System.out.println(inputNum);
	}
	
	public void printString(String string) {
		System.out.println("글자수 : " + string.length());
		System.out.println(string);
	}
	
}
