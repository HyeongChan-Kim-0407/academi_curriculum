package khc.springboot.project5.webSocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project5.webSocket.dto.MessageDto;

@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private HttpSession session;
	
	@MessageMapping("/chatMsg")
	public void sendMessage(MessageDto msgDto) {
		System.out.println("/chatMsg");
		System.out.println("msg : " + msgDto);
		System.out.println(msgDto.getType());
		System.out.println(msgDto.getContent());
		msgDto.setSender("송신자"); // Session에서 사용자 ID를 가져와서 메시지 전송자 설정 (현재는 임의값 사용)
		// 서버가 접속중인 모든 클라이언트에게 메시지를 전파
		messagingTemplate.convertAndSend("/ServerToClient/publicMsg", msgDto);
	}
	
	
}
