package khc.springboot.project7.total.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import khc.springboot.project7.total.dto.MemberDto;
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
	
	@OneToMany(mappedBy = "member", cascade=CascadeType.ALL)
	private List<Location> locations;
	
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
	
	public Member(MemberDto memberDto) {
		this.id = memberDto.getId();
		this.mid = memberDto.getMid();
		this.mpw = memberDto.getMpw();
		this.mname = memberDto.getMname();
		this.memail = memberDto.getMemail();
		this.mjoindate = memberDto.getMjoindate();
		this.mroute = memberDto.getMroute();
	}

	public Member(MemberDto loginUser, List<Location> locations) {
		this.id = loginUser.getId();
		this.mid = loginUser.getMid();
		this.mpw = loginUser.getMpw();
		this.mname = loginUser.getMname();
		this.memail = loginUser.getMemail();
		this.mjoindate = loginUser.getMjoindate();
		this.mroute = loginUser.getMroute();
		this.locations = locations;
	}
	
}
