package khc.springboot.project7.total.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import khc.springboot.project7.total.domain.Location;
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
	
	public MemberForm(MemberDto memberDto) {
		this.mid = memberDto.getMid();
		this.mpw = memberDto.getMpw();
		this.mname = memberDto.getMname();
		this.memail = memberDto.getMemail();
		this.mjoindate = memberDto.getMjoindate();
		this.mroute = memberDto.getMroute();
	}
	
}
