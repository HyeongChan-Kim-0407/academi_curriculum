package Academy.Project.Rental.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberForm {
	
		@NotBlank(message = "아이디는 필수 입니다")
		private String mid;

		@NotBlank(message = "비밀번호는 필수 입니다")
		private String mpw;

		@NotBlank(message = "이름은 필수 입니다")
		private String mname;
		private String mphone;
		private String memail;

		private String maddress;
	}


