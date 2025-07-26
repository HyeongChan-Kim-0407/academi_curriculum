package Academy.Project.Rental.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import Academy.Project.Rental.domain.Request;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestDto {
	
	private Long id;
	private String mid; // 요청자 아이디
	private Long placeId; // 대여 요청 장소 번호
	private LocalDate bdate; // 대여 날짜
	private String r11_13; // 11시 ~ 13시
	private String r13_15; // 13시 ~ 15시
	private String r15_17; // 15시 ~ 17시
	private String r17_19; // 17시 ~ 19시
	private String r19_21; // 19시 ~ 21시
	private String r21_23; // 21시 ~ 23시
	
	private LocalDateTime rdate; // 요청 작성일
	private String rstatus; // 요청 상태 (예: 요청완료, 승인대기 등)
	
	private boolean isSeller; // 게시글 등록자 여부
	
	public RequestDto() {
		// 기본 생성자
	}
	
	public RequestDto(Request request) {
		this.id = request.getId();
		this.mid = request.getMember().getMid();
		this.placeId = request.getPlace().getId();
		this.bdate = request.getBdate(); // LocalDate를 String으로 변환
		if(request.getR11_13().equals("O")) {
			this.r11_13 = "11시 ~ 13시";
		}
		if(request.getR13_15().equals("O")) {
			this.r13_15 = "13시 ~ 15시";
		}
		if(request.getR15_17().equals("O")) {
			this.r15_17 = "15시 ~ 17시";
		}
		if(request.getR17_19().equals("O")) {
			this.r17_19 = "17시 ~ 19시";
		}
		if(request.getR19_21().equals("O")) {
			this.r19_21 = "19시 ~ 21시";
		}
		if(request.getR21_23().equals("O")) {
			this.r21_23 = "21시 ~ 23시";
		}
//		this.r11_13 = request.getR11_13();
//		this.r13_15 = request.getR13_15();
//		this.r15_17 = request.getR15_17();
//		this.r17_19 = request.getR17_19();
//		this.r19_21 = request.getR19_21();
//		this.r21_23 = request.getR21_23();
		this.rdate = request.getRdate();
		this.rstatus = request.getRstatus();
		
	}
	
}
