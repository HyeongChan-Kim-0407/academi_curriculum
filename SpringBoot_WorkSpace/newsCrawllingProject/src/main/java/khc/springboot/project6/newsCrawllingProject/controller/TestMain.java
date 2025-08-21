package khc.springboot.project6.newsCrawllingProject.controller;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class TestMain {

	public static void main(String[] args) {
		
		String prompt = "다음에 제공되는 기사내용을 3줄로 요약해줘. 요약은 특정 관점 없이 중립적인 문체로 작성하고,"
					  + "주관적인 판단이나 의견은 제외하고 사실만을 한국어로 요약해줘. \n";
		
		Client client = Client.builder().apiKey("AIzaSyA8qDx7aAEdFkfBAooB8-sJ3iZBIh8VNyU").build();

	    GenerateContentResponse response =
	        client.models.generateContent(
	            "gemini-2.5-flash",
	            prompt + "기사 본문 ",
	            null);

	    System.out.println(response.text());
		
	}

}
