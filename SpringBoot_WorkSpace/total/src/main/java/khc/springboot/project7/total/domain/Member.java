package khc.springboot.project7.total.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import khc.springboot.project7.total.dto.MemberForm;
import lombok.Getter;

@Entity
@Getter
public class Member {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true)
	private String mid;
	
	@Column(nullable = false)
	private String mpw;
	
	@Column(nullable = false)
	private String mname;
	
	private String memail;
	private LocalDate mjoindate;
	private String mroute;
	
	public Member() {
		
	}
	
	public Member(MemberForm memberForm) {
		this.mid = memberForm.getMid();
		this.mpw = memberForm.getMpw();
		this.mname = memberForm.getMname();
		this.memail = memberForm.getMemail();
		this.mjoindate = memberForm.getMjoindate();
		this.mroute = memberForm.getMroute();
	}
	
}
