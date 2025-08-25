package khc.arranged.dependency.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 이 클래스의 모든 메소드는 페이지 없이 데이터만 응답함을 명시
public class RestApiController {
	
	@GetMapping("/getURL3")
	public List<String> getData3() {
		List<String> dataList = new ArrayList<>();
		dataList.add("a");
		dataList.add("b");
		return dataList; // 리스트 데이터 dataList를 응답 
	}
	
}
