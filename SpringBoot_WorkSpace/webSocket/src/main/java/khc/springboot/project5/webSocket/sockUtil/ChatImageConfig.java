package khc.springboot.project5.webSocket.sockUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ChatImageConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// <img src="/chatImage/파일명.확장자">
		registry.addResourceHandler("/chatImage/**") // src 속성에서 사용할 주소
				.addResourceLocations("file:" + "C:/chatImage"); // 실제 파일 경로
	}
	
}
