package khc.springboot.project5.webSocket.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import khc.springboot.project5.webSocket.domain.Message;
import khc.springboot.project5.webSocket.dto.MessageDto;
import khc.springboot.project5.webSocket.repository.ChatRepository;
import khc.springboot.project5.webSocket.service.ChatService;
import khc.springboot.project5.webSocket.sockUtil.ConnectUser;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private ConnectUser connectUser;
	
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
	
	// 특정 클라이언트를 대상으로 접속 목록 메세지 전송
	@MessageMapping("/reqList")
	public void userListResponse(String username) {
		messagingTemplate.convertAndSend("/ServerToClient/resList/"+username, connectUser.getConnectList().values());
	}
	
	@PostMapping("/img")
	@ResponseBody
	public String uploadImage(@RequestParam("imgFile") MultipartFile imgFile) {
		System.out.println("이미지명 : " + imgFile.getOriginalFilename());
		
		String imgSavePath = chatService.chatImageupload(imgFile);
		
		return imgSavePath;
	}
	
	@MessageMapping("/ChatImage")
	public void sendImage(MessageDto msgDto) {
		System.out.println("/chatMsg");
		// ChatDto에 채팅화면에 표시할 시간(msgtime) 추가
		LocalDateTime nowTime = LocalDateTime.now();
		String msgTime = nowTime.format(DateTimeFormatter.ofPattern("a h:mm", Locale.KOREAN));
		msgDto.setMsgTime(msgTime);
		
		Message msg = new Message(msgDto);
		
		chatRepository.save(msg);
		
		System.out.println("msg : " + msgDto);
		// 서버가 접속중인 모든 클라이언트에게 메시지를 전파
		messagingTemplate.convertAndSend("/ServerToClient/ChatImage", msgDto);
	}
	
}
