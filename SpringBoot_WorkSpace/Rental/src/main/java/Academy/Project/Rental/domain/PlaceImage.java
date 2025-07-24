package Academy.Project.Rental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class PlaceImage {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String pfilename; // 이미지 파일 이름
	
	@ManyToOne
	@JoinColumn(name = "placeId")
	private Place place; // 해당 이미지가 속한 장소 정보
	
	public PlaceImage() {
		
	}
	
}
