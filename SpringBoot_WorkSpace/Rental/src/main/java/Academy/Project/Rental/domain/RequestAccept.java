package Academy.Project.Rental.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequestAccept {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requestId")
	private Request request;
	
	private LocalDate adate; // 수락일

	public RequestAccept() {

	}
	
	public RequestAccept(Request request) {
		this.request = request;
		this.adate = LocalDate.now(); // 현재 날짜로 수락일 설정
	}

}
