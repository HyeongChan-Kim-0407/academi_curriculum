package khc.springboot.project2.orders.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ProductImageConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// <img src="/productImage/파일명.확장자">
		registry.addResourceHandler("/productImage/**").addResourceLocations("file:" + "C:/productImages"); // **은 하위 모든 경로 포함
		
	}

}
