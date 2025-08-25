package khc.arranged.dependency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableScheduling - 스케쥴 기능
@SpringBootApplication
public class DependencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DependencyApplication.class, args);
	}

}
