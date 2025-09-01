package khc.springboot.project7.total.dto;

import java.time.LocalDate;

import khc.springboot.project7.total.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDto {
	
	private String mid;
	private String mpw;
	private String mname;
	private String memail;
	private LocalDate mjoindate;
	private String mroute;
	
	public MemberDto() {
		
	}
	
	public MemberDto(Member member) {
		this.mid = member.getMid();
		this.mpw = member.getMpw();
		this.mname = member.getMname();
		this.memail = member.getMemail();
		this.mjoindate = member.getMjoindate();
		this.mroute = member.getMroute();
	}
	
}
