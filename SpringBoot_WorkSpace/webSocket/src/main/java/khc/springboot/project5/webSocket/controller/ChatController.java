package khc.springboot.project5.webSocket.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project5.webSocket.domain.Message;
import khc.springboot.project5.webSocket.dto.MessageDto;
import khc.springboot.project5.webSocket.repository.ChatRepository;

@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@MessageMapping("/chatMsg")
	public void sendMessage(MessageDto msgDto) {
		System.out.println("/chatMsg");
		// ChatDto에 채팅화면에 표시할 시간(msgtime) 추가
		LocalDateTime nowTime = LocalDateTime.now();
		String msgTime = nowTime.format(DateTimeFormatter.ofPattern("a h:mm", Locale.KOREAN));
		msgDto.setMsgTime(msgTime);
		
		Message msg = new Message(msgDto);
		
		chatRepository.save(msg);
		
		System.out.println("msg : " + msgDto);
		// 서버가 접속중인 모든 클라이언트에게 메시지를 전파
		messagingTemplate.convertAndSend("/ServerToClient/publicMsg", msgDto);
	}
	
	
}
