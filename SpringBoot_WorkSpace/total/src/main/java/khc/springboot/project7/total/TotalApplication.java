package khc.springboot.project7.total;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TotalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TotalApplication.class, args);
	}

}
