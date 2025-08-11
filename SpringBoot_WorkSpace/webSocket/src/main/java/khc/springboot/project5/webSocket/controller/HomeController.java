package khc.springboot.project5.webSocket.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import khc.springboot.project5.webSocket.dto.MessageDto;
import khc.springboot.project5.webSocket.sockUtil.SocketConfig;

@Controller
public class HomeController {

    private final SocketConfig socketConfig;

    HomeController(SocketConfig socketConfig) {
        this.socketConfig = socketConfig;
    }
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
}
