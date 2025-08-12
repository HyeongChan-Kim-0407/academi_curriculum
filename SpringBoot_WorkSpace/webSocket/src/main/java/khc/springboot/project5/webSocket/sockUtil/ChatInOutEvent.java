package khc.springboot.project5.webSocket.sockUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class ChatInOutEvent {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	
	
	@EventListener
	public void chatConnect(SessionConnectEvent connectEvent) {
		System.out.println("WebSocket 접속");
		// 다른 클라이언트에게 입장 메시지 전송
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(connectEvent.getMessage());
		String username = (String)accessor.getSessionAttributes().get("username");
		messagingTemplate.convertAndSend("/ServerToClient/ChatInOut", username + "님이 입장했습니다.");
	}
	
	@EventListener
	public void chatDisconnect(SessionDisconnectEvent disconnectEvent) {
		System.out.println("WebSocket 접속해제");
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
		String username = (String)accessor.getSessionAttributes().get("username");
		messagingTemplate.convertAndSend("/ServerToClient/ChatInOut", username + "님이 퇴장했습니다.");
	}
	
}
