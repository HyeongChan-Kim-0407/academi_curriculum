package khc.springboot.project5.webSocket.sockUtil;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ConnectUser {
	
	private Map<String, String> connectList = new HashMap<>();
	// 접속세션 : 접속자아이디
	
	public void addUser(String sessionid, String username) {
		connectList.put(sessionid, username);
	}
	
	public void removeUser(String sessionid) {
		connectList.remove(sessionid);
	}
}
