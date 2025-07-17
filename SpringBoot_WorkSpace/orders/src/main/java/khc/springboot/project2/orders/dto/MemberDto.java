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
	private String mpostcode; 		// 우편번호
	private String mroadAddress; 	// 도로명 주소
	private String mjibunAddress; 	// 지번 주소
	private String mdetailAddress; 	// 상세 주소
	private String mextraAddress; 	// 참고 항목
	private String memail;
	private LocalDateTime mjoindate;
	
	
	public MemberDto() {
		
	}

	public MemberDto(Member member) {
		this.mid = member.getMid();
		this.mpw = member.getMpw();
		this.mname = member.getMname();
		this.mpostcode = member.getMpostcode();
		this.mroadAddress = member.getMroadAddress();
		this.mjibunAddress = member.getMjibunAddress();
		this.mdetailAddress = member.getMdetailAddress();
		this.mextraAddress = member.getMextraAddress();
		this.memail = member.getMemail();
		this.mjoindate = member.getMjoindate();
	}
	
}
