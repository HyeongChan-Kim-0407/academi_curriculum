package Academy.Project.Rental.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class RequestForm {
	
	private String r11_13; // 11시 ~ 13시
	private String r13_15; // 13시 ~ 15시
	private String r15_17; // 15시 ~ 17시
	private String r17_19; // 17시 ~ 19시
	private String r19_21; // 19시 ~ 21시
	private String r21_23; // 21시 ~ 23시
	private LocalDate bdate; // 대여 날짜
	private LocalDateTime rdate = LocalDateTime.now(); // 요청 작성일
	

}
