package khc.springboot.project2.orders.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	private String mpostcode; 		// 우편번호
	private String mroadAddress; 	// 도로명 주소
	private String mjibunAddress; 	// 지번 주소
	private String mdetailAddress; 	// 상세 주소
	private String mextraAddress; 	// 참고 항목
	
	private String memail; // 이메일
	
	private LocalDateTime mjoindate; // 가입일
	
	@OneToMany(mappedBy = "member")
	private List<Product> productList; // 회원이 등록한 상품 목록
	
	@OneToMany(mappedBy = "member")
	private List<OrderRequest> orderRequestList; // 회원이 신청한 거래 목록
	
	@OneToMany(mappedBy = "member")
	private List<Orders> orderList; // 회원이 완료한 거래 목록
	
	public Member() {
		
	}
	public Member(MemberForm memberForm) {
		this.mid = memberForm.getMid();
		this.mpw = memberForm.getMpw();
		this.mname = memberForm.getMname();
		this.mpostcode = memberForm.getMpostcode();
		this.mroadAddress = memberForm.getMroadAddress();
		this.mjibunAddress = memberForm.getMjibunAddress();
		this.mdetailAddress = memberForm.getMdetailAddress();
		this.mextraAddress = memberForm.getMextraAddress();
		this.memail = memberForm.getMemail();
		this.mjoindate = LocalDateTime.now(); // 현재 시간으로 가입일 설정
	}
}
