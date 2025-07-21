package khc.springboot.project2.orders.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequestDto {
	
	private Long productId; // 거래 신청한 상품 번호
	
	private Long memberId; // 거래 신청한 회원 번호
	
	private String mid; // 거래 신청한 회원 아이디
	
	private String requestState; // 거래 신청 상태

}
