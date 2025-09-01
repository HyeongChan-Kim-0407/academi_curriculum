package khc.springboot.project7.total.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberForm {
	
	@NotBlank(message = "아이디는 필수 입력 사항입니다.")
	private String mid;
	@NotBlank(message = "비밀번호는 필수 입력 사항입니다.")
	private String mpw;
	@NotBlank(message = "이름은 필수 입력 사항입니다.")
	private String mname;
	
	private String memail;
	private LocalDate mjoindate;
	private String mroute;
	
	public MemberForm() {
		
	}
	
}
