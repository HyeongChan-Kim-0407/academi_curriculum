package khc.springboot.project2.orders.dto;

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
	@NotBlank(message = "이름이 입력되지 않았습니다.")
	private String mname;
	
	private String mpostcode; 		// 우편번호
	private String mroadAddress; 	// 도로명 주소
	private String mjibunAddress; 	// 지번 주소
	private String mdetailAddress; 	// 상세 주소
	private String mextraAddress; 	// 참고 항목
	
	private String memail;
	
}
