package khc.springboot.project2.orders.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

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
public class OrderRequest {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member; // 거래 신청을 한 회원 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product; 
	
	private LocalDateTime requestdate; // 거래 신청 날짜
	
	private String requestState = "신청완료"; // 상태 : 승인, 거절, 신청완료
	
	public OrderRequest() {
		
	}
	
	public OrderRequest(Member member, Product product) {
		this.member = member;
		this.product = product;
		this.requestdate = LocalDateTime.now(); // 거래 신청 날짜는 현재 시간으로 설정
		this.requestState = "신청완료"; // 기본 상태는 신청 완료
	}
}
