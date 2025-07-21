package khc.springboot.project2.orders.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Orders {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private Member member; // 거래가 체결된 회원
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productId")
	private Product product; // 거래가 체결된 상품
	
	private LocalDateTime orderdate; // 거래 날짜
	
	
	
	public Orders() {
		
	}
	
}
