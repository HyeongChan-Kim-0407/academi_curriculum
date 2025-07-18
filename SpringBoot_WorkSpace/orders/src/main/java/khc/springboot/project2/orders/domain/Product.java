package khc.springboot.project2.orders.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import khc.springboot.project2.orders.dto.ProductForm;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String pname;	// 상품명
	private int pprice;		// 상품 가격
	private String ptype; 	// 상품 종류
	@Column(length = 1000)
	private String pinfo; 	// 상품 정보
	private String pstate; 	// 상품 상태
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="memberId")
	private Member member; // 상품을 등록한 회원
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL) // cascade = CascadeType.ALL : 연관된 Entity도 함께 save
	private List<ProductImage> imageList = new ArrayList<>(); // 상품 이미지 목록
	
	public Product() {
		
	}
	
	public Product(ProductForm productForm, Member member) {
		this.pname = productForm.getPname();
		this.pprice = productForm.getPprice();
		this.ptype = productForm.getPtype();
		this.pinfo = productForm.getPinfo();
		this.pstate = "판매중"; // 기본 상태는 판매중
		this.member = member; // 상품을 등록한 회원 정보 설정
	}
	
}
