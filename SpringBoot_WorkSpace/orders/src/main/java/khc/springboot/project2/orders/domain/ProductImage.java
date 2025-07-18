package khc.springboot.project2.orders.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ProductImage {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String pfilename;
	
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	public ProductImage() {
		// 기본 생성자
	}
	
	public ProductImage(String pfilename, Product product) {
		this.pfilename = pfilename;
		this.product = product;
	}
}
