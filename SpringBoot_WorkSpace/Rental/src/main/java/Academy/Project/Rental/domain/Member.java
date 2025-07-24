package Academy.Project.Rental.domain;

import java.util.List;

import Academy.Project.Rental.dto.MemberForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String mid;
	@Column(nullable = false)
	private String mpw;
	private String mname;
	private String mphone;
	private String memail; 
	private String maddress;
	private String mtype;	// 개인회원, 기관회원
	
	@OneToMany(mappedBy = "member")
	private List<Interests> minterests;	// 회원 관심사
	
	@OneToMany(mappedBy = "member")
	private List<Request> requestList; // 회원이 작성한 요청 목록
	
	@OneToMany(mappedBy = "member")
	private List<RequestAccept> AcceptList; // 회원이 수락한 요청 목록
	
	@OneToMany(mappedBy = "member")
	private List<Reply> replyList; // 회원이 작성한 댓글 목록
	
	@OneToMany(mappedBy = "pmember")
	private List<Place> placeList;
	
	
	public Member() {
		
	}
	
	public Member(MemberForm memberform) {
		this.mid = memberform.getMid();
		this.mpw = memberform.getMpw();
		this.mname = memberform.getMname();
		this.mphone = memberform.getMphone();
		this.memail = memberform.getMemail();
		this.maddress = memberform.getMaddress();
		this.mtype = memberform.getMtype();
	}
}