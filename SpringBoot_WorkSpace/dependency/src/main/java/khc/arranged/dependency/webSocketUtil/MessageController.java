package khc.arranged.dependency.webSocketUtil;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
	
	@MessageMapping("/message") // webSocketConfig 클래스에서 등록한 주소 (/ClientToServer/message)에서 요청이 들어올 때 동작
	public void message(String textMsg) { /* 이 메소드의 매개변수 > 클라이언트에서 전송하는 메시지 */
		System.out.println("클라이언트에서 전송한 메시지 : " + textMsg);
		// 서버가 클라이언트들에게 메시지를 전송 (채팅)
		// 서버가 메시지를 받으면 실행할 코드
	}
	
}
