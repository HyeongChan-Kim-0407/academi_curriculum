package khc.arranged.dependency.webSocketUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import khc.arranged.dependency.dto.MessageDto;

@Controller
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/message") // webSocketConfig 클래스에서 등록한 주소 (/ClientToServer/message)에서 요청이 들어올 때 동작
	public void message(MessageDto msgDto) { /* 이 메소드의 매개변수 > 클라이언트에서 전송하는 메시지 */
		System.out.println("클라이언트에서 전송한 메시지 : " + msgDto);
		// 서버가 메시지를 받으면 실행할 코드
		/* 하나의 주소에서 데이터에 따라 실행 코드를 다양화
		if (msgDto.getData1().equals("chat")) {
			// 서버가 클라이언트들에게 메시지를 전송 (채팅)
			
		}else if (msgDto.getData1().equals("alert")) {
			// 서버가 클라이언트들에게 메시지를 전송 (알림)
			
		}	
		*/
		
		// WebSocketConfig 클래스에서 등록한 주소 (/ServerToClient/구독주소)로 (전송할 메시지) 전송
//		messagingTemplate.convertAndSend("/ServerToClient/sampleSub", "서버가 보낸 메시지");
		
	}
	
	/* 
	 용도에 따라 주소를 분리
	@MessageMapping("/chat")
	public void chat() {
		
	}
	
	@MessageMapping("/alert")
	public void alert() {
		
	}
	 */
	
	@MessageMapping("/changeLGrey")
	public void changeLGrey() {
		messagingTemplate.convertAndSend("/ServerToClient/bg-lgrey", "LGrey");
	}
	
	@MessageMapping("/changeSky")
	public void changeSkyBlue() {
		messagingTemplate.convertAndSend("/ServerToClient/bg-sky", "SkyBlue");
	}
	
}
