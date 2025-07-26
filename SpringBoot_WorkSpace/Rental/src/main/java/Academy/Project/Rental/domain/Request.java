package Academy.Project.Rental.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Academy.Project.Rental.dto.RequestForm;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member; // 요청자
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeId")
	private Place place; // 대여 요청 장소
	
	private LocalDate bdate; //
	
	private String r11_13; // 11시 ~ 13시
	private String r13_15; // 13시 ~ 15시
	private String r15_17; // 15시 ~ 17시
	private String r17_19; // 17시 ~ 19시
	private String r19_21; // 19시 ~ 21시
	private String r21_23; // 21시 ~ 23시
	
	private LocalDateTime rdate; // 요청 작성일
	private String rstatus;
	
	public Request() {
		
	}
	
	public Request(RequestForm requestForm, Member member, Place place) {
		this.member = member;
		this.place = place;
		this.r11_13 = requestForm.getR11_13();
		this.r13_15 = requestForm.getR13_15();
		this.r15_17 = requestForm.getR15_17();
		this.r17_19 = requestForm.getR17_19();
		this.r19_21 = requestForm.getR19_21();
		this.r21_23 = requestForm.getR21_23();
		this.rdate = requestForm.getRdate();
		this.bdate = requestForm.getBdate();
		this.rstatus = "요청완료";
	}

}
