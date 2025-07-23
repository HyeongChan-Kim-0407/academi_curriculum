package Academy.Project.Rental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Request {

	@Id
	@GeneratedValue
	private Long id;

	private String rtitle;
	private String rcontent;
	@ManyToOne
	private Member member; // 요청자
	
	private String rdate; // 요청 작성일
	private String needDate; // 필요일
	private String rstatus;

}
