package khc.springboot.project5.webSocket.sockUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import khc.springboot.project5.webSocket.domain.Message;
import khc.springboot.project5.webSocket.dto.MessageDto;
import khc.springboot.project5.webSocket.repository.ChatRepository;

@Component
public class ChatInOutEvent {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@EventListener
	public void chatConnect(SessionConnectEvent connectEvent) {
		System.out.println("WebSocket 접속");
		// 다른 클라이언트에게 입장 메시지 전송
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(connectEvent.getMessage());
		String username = (String)accessor.getSessionAttributes().get("username");
		MessageDto msgDto = new MessageDto();
		msgDto.setType("inout");
		msgDto.setSender(username);
		msgDto.setContent("입장");
		Message msg = new Message(msgDto);
		chatRepository.save(msg);
		messagingTemplate.convertAndSend("/ServerToClient/ChatInOut", msgDto);
	}
	
	@EventListener
	public void chatDisconnect(SessionDisconnectEvent disconnectEvent) {
		System.out.println("WebSocket 접속해제");
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
		String username = (String)accessor.getSessionAttributes().get("username");
		MessageDto msgDto = new MessageDto();
		msgDto.setType("inout");
		msgDto.setSender(username);
		msgDto.setContent("퇴장");
		Message msg = new Message(msgDto);
		chatRepository.save(msg);
		messagingTemplate.convertAndSend("/ServerToClient/ChatInOut", msgDto);
	}
	
}
