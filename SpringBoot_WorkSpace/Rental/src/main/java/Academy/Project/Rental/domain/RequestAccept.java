package Academy.Project.Rental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequestAccept {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "placeId")
	private Place place;

	@ManyToOne
	@JoinColumn(name = "memberId")
	private Member member; // 수락자

	private String adate; // 수락일

	public RequestAccept() {

	}

}
