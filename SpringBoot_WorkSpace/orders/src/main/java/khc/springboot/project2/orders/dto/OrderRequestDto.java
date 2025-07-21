package khc.springboot.project2.orders.dto;

import khc.springboot.project2.orders.domain.OrderRequest;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequestDto {
	
	private Long productId; // 거래 신청한 상품 번호
	
	private Long memberId; // 거래 신청한 회원 번호
	
	private String mid; // 거래 신청한 회원 아이디
	
	private String requestState; // 거래 신청 상태
	
	public OrderRequestDto() {
		
	}
	
	public OrderRequestDto(OrderRequest orderRequest) {
		this.productId = orderRequest.getProduct().getId();
		this.memberId = orderRequest.getMember().getId();
		this.mid = orderRequest.getMember().getMid();
		this.requestState = orderRequest.getRequestState();
	}

}
