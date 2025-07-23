package Academy.Project.Rental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Reply {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Request request;	// 요청
	@ManyToOne
	private Member member;		// 작성자
	private String rcontent;	// 댓글 내용
	private String rdate;		// 댓글 작성일
	
	public Reply() {
		
	}
	
}
