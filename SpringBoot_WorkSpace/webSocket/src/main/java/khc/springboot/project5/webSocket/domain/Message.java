package khc.springboot.project5.webSocket.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import khc.springboot.project5.webSocket.dto.MessageDto;
import lombok.Getter;

@Entity
@Getter
public class Message {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String sender;
	
	@Column(length = 3000)
	private String content;
	private String type; // 메시지 유형 (chat, inout)
	private String msgTime; // 채팅 화면에 표시할 시간
	private LocalDateTime msgDate; // 메시지 날짜 및 시간
	
	public Message() {
		
	}
	
	public Message(MessageDto messageDto) {
		this.sender = messageDto.getSender();
		this.content = messageDto.getContent();
		this.type = messageDto.getType();
		this.msgTime = messageDto.getMsgTime();
		msgDate = LocalDateTime.now(); // 현재 시간으로 초기화
	}
	
	
}
