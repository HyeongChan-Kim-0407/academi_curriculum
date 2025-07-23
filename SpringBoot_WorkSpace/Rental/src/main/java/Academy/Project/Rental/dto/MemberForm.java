package Academy.Project.Rental.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
	
	@NotBlank(message = "아이디는 필수입니다.")
	private String mid;
	@NotBlank(message = "비밀번호는 필수입니다.")
	private String mpw;
	@NotBlank(message = "이름은 필수입니다.")
	private String mname;
	private String mphone;
	private String memail; 
	private String maddress;
	
	public MemberForm() {
		
	}
	
}
