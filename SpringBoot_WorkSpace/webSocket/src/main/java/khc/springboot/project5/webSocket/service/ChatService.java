package khc.springboot.project5.webSocket.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ChatService {
	
	private final String SAVEPATH = "C:/chatImage";
	
	public String chatImageupload(MultipartFile imgFile){
		String originalFileName = imgFile.getOriginalFilename(); // 클라이언트에서 업로드한 원본 파일명		
		// 새로운 파일명 작성 (UUID 사용)
		// 1. 원본 파일명의 확장자 시작 위치(.) 확인
		int dotIndex = originalFileName.lastIndexOf(".");
		// 2. 원본 파일명의 확장자 추출
		String ext = originalFileName.substring(dotIndex); // 확장자 추출
		// 3. uuid 생성
		String uuid = UUID.randomUUID().toString();
		// uuid와 확장자 결합
		String newFileName = uuid + ext; // 새로운 파일명 생성
		System.out.println("새 파일명 : " + newFileName);
		// 4. 파일 저장
		try {
			File file = new File(SAVEPATH, newFileName);
			imgFile.transferTo(file);
			return "/chatImage/" + newFileName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
