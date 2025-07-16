package khc.springboot.project2.orders.dto;

import java.time.LocalDateTime;

import khc.springboot.project2.orders.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDto {
	
	private String mid;
	private String mpw;
	private String mname;
	private String maddr;
	private String memail;
	private LocalDateTime mjoindate;
	
	
	public MemberDto() {
		
	}

	public MemberDto(Member member) {
		this.mid = member.getMid();
		this.mpw = member.getMpw();
		this.mname = member.getMname();
		this.maddr = member.getMaddr();
		this.memail = member.getMemail();
		this.mjoindate = member.getMjoindate();
	}
	
}
