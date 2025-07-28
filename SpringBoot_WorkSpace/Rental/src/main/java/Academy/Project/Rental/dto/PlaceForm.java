package Academy.Project.Rental.dto;


import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlaceForm {

	@NotBlank(message = "장소 이름은 필수 입니다")
	private String ptitle; // 장소 이름
	
	@NotBlank(message = "장소 정보는 필수 입니다")
	private String pinfo; // 장소 정보
	
	@NotBlank(message = "선호 장소를 선택 해주세요")
	private String ptype; // 장소 유형 (예: 파티룸, 연습실 등)
	
	@NotBlank(message = "장소 위치는 필수 입니다")
	private String plocation; // 장소 위치	
	
	private	int pprice; // 대여 가격 (시간당?일당?)

	private MultipartFile[] Pfiles;
	
}
