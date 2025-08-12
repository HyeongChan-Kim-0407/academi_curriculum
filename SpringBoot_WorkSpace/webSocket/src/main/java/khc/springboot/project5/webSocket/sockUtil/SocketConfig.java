package khc.springboot.project5.webSocket.sockUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class SocketConfig implements WebSocketMessageBrokerConfigurer {
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws-chatin") // ws : socket 이용 명시
				.addInterceptors(new HttpSessionHandshakeInterceptor()) // HttpSession을 사용하기 위한 인터셉터 등록
				.withSockJS(); // SockJs 사용
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/ClientToServer"); // 클라이언트가 서버에게 메시지를 보낼 때 사용
		
		registry.enableSimpleBroker("/ServerToClient"); // 서버가 클라이언트에게 메시지를 보낼 때 사용
	}
	
	
	
}
