package khc.springboot.project2.orders.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberForm {
	
	private String mid;
	private String mpw;
	private String mname;
	private String maddr;
	private String memail;
	
}
