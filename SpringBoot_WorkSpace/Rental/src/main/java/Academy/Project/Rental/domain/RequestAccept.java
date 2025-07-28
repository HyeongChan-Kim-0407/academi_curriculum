package Academy.Project.Rental.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member; // 요청자
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeId")
	private Place place;
	
	private LocalDate bdate; //
	
	private String r11_13; // 11시 ~ 13시
	private String r13_15; // 13시 ~ 15시
	private String r15_17; // 15시 ~ 17시
	private String r17_19; // 17시 ~ 19시
	private String r19_21; // 19시 ~ 21시
	private String r21_23; // 21시 ~ 23시
	
	private LocalDate adate; // 수락일

	public RequestAccept() {

	}
	
	public RequestAccept(Request request) {
		this.member = request.getMember();
		this.place = request.getPlace();
		this.bdate = request.getBdate();
		this.r11_13 = request.getR11_13();
		this.r13_15 = request.getR13_15();
		this.r15_17 = request.getR15_17();
		this.r17_19 = request.getR17_19();
		this.r19_21 = request.getR19_21();
		this.r21_23 = request.getR21_23();
		this.adate = LocalDate.now(); // 현재 날짜로 수락일 설정
	}

}
