package Academy.Project.Rental.dto;

import java.time.LocalDate;

import Academy.Project.Rental.domain.RequestAccept;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAcceptDto {

	private Long id;
	private Long mid;
	private Long pid;

	private LocalDate bdate; //

	private String r11_13; // 11시 ~ 13시
	private String r13_15; // 13시 ~ 15시
	private String r15_17; // 15시 ~ 17시
	private String r17_19; // 17시 ~ 19시
	private String r19_21; // 19시 ~ 21시
	private String r21_23; // 21시 ~ 23시
	private LocalDate adate;

	public RequestAcceptDto() {
		// 기본 생성자
	}

	public RequestAcceptDto(RequestAccept ra) {
		this.id = ra.getId();
		this.mid = ra.getMember().getId();
		this.pid = ra.getPlace().getId();
		this.bdate = ra.getBdate();
		this.r11_13 = ra.getR11_13();
		this.r13_15 = ra.getR13_15();
		this.r15_17 = ra.getR15_17();
		this.r17_19 = ra.getR17_19();
		this.r19_21 = ra.getR19_21();
		this.r21_23 = ra.getR21_23();
		this.adate = ra.getAdate();
	}

}
