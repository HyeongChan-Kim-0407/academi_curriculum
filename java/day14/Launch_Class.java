package day14;

public abstract class Launch_Class {

	public static void main(String[] args) {
		
		Implement_Class obj1 = new Implement_Class();
		Implement_Class2 obj2 = new Implement_Class2();
		
		obj1.printNumber(0);
		obj1.printString("매개변수");
		
		obj2.printNumber(0);
		obj2.printString("매개변수"); 
		
		Interface object01 = new Implement_Class();
		object01.printNumber(0);
		object01 = new Implement_Class2();
		object01.printNumber(0);
		
	}

}
