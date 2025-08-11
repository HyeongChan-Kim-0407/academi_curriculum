package khc.springboot.project5.webSocket.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MessageDto {
	
	private String type; 	// 메시지 종류
	private String content; // 메시지 내용
	private String sender; 	// 메시지를 전송한 사람
//	private LocalDateTime sendTime = LocalDateTime.now(); // 메시지를 전송한 시간
	
}
