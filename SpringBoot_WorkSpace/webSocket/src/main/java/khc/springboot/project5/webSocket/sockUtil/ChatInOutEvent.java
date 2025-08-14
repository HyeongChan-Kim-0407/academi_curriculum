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
	
	@Autowired
	private ConnectUser connectUser;
	
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
		
		// 전체 접속자 명단 전송
		// 세션 ID 확인
		String sessionid = accessor.getSessionId();
		System.out.println("입장한 세션 id : " + sessionid);
		// 접속 목록에 추가
		connectUser.addUser(sessionid, username);
		// 접속 목록을 전송
		messagingTemplate.convertAndSend("/ServerToClient/userList", connectUser.getConnectList().values() );
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

		// 전체 접속자 명단 전송
		String sessionid = accessor.getSessionId();
		System.out.println("퇴장한 세션 id : " + sessionid);	
		// 접속 목록에서 삭제
		connectUser.removeUser(sessionid);
		// 접속 목록을 전송
		messagingTemplate.convertAndSend("/ServerToClient/userList", connectUser.getConnectList().values() );
	}
	
}
