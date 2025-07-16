package khc.springboot.project2.orders.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import khc.springboot.project2.orders.dto.MemberForm;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue
	private Long id; // 회원번호
	
	@Column(nullable = false, unique = true)
	private String mid; // 아이디
	
	@Column(nullable = false)
	private String mpw; // 비밀번호
	
	private String mname; // 이름
	private String maddr; // 주소
	private String memail; // 이메일
	
	private LocalDateTime mjoindate; // 가입일
	
	public Member() {
		
	}
	public Member(MemberForm memberForm) {
		this.mid = memberForm.getMid();
		this.mpw = memberForm.getMpw();
		this.mname = memberForm.getMname();
		this.maddr = memberForm.getMaddr();
		this.memail = memberForm.getMemail();
		this.mjoindate = LocalDateTime.now(); // 현재 시간으로 가입일 설정
	}
}
