package day03;

public class for4 {

	public static void main(String[] args) {
		// 
		
		String words = "JavaPythonOracle";
		/*System.out.println(words.charAt(0));
		System.out.println(words.charAt(1));
		System.out.println(words.charAt(2));
		System.out.println(words.length());
		
		System.out.println(words.charAt(words.length() - 1));
		*/
		 
		int aCount = 0;
		for (int i = 0; i < words.length(); i++) {
			//`System.out.println(words.charAt(i));
			//char oneWord = words.charAt(i);
			if(words.charAt(i) == 'a') {
				aCount++;
				System.out.println(i);
			}
		}
		// 소문자 a 갯수 출력
		System.out.println("a의 개수 : " + aCount );
	}

}
