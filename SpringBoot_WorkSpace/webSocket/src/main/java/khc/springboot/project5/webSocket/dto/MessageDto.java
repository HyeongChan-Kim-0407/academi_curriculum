package khc.springboot.project5.webSocket.dto;

import khc.springboot.project5.webSocket.domain.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MessageDto {
	
	private String sender; 	// 메시지를 전송한 사람
	private String content; // 메시지 내용
	private String type; 	// 메시지 종류
	private String msgTime;
	
	public MessageDto() {
		
	}
	
	public MessageDto(Message message) {
		this.sender = message.getSender();
		this.content = message.getContent();
		this.type = message.getType();
		this.msgTime = message.getMsgTime();
	}
	
	
}
