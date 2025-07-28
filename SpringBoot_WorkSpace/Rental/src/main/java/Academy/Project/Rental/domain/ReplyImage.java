package Academy.Project.Rental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ReplyImage {

	@Id
	@GeneratedValue
	private Long id;

	private String rfilename;

	@ManyToOne(fetch = FetchType.LAZY)
	private Reply reply;
}
