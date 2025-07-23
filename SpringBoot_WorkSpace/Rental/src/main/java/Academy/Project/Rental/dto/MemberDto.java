package Academy.Project.Rental.dto;

import Academy.Project.Rental.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberDto {

	private Long id;
	private String mid;
	private String mpw;
	private String mname;
	private String mphone;
	private String memail;
	private String maddress;
	
	public MemberDto(Member member) {
		this.id = member.getId();
		this.mid = member.getMid();
		this.mpw = member.getMpw();
		this.mname = member.getMname();
		this.mphone = member.getMphone();
		this.memail = member.getMemail();
		this.maddress = member.getMaddress();
	}
}