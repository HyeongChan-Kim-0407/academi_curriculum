package khc.springboot.project2.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project2.orders.domain.Member;
import khc.springboot.project2.orders.domain.Product;
import khc.springboot.project2.orders.dto.ProductForm;
import khc.springboot.project2.orders.repository.MemberRepository;
import khc.springboot.project2.orders.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	/* 상품 등록기능 */
	public void registerProduct(ProductForm productForm, String loginId) {
		// 로그인 된 회원의 아이디로 MemberRepository에서 Member Entity를 조회
		Member loginMember = memberRepository.findByMid(loginId);
		// ProductForm과 Member Entity를 Product Entity로 변환
		Product product = new Product(productForm, loginMember);
		// 변환된 Product Entity를 Product Repository에 저장(save)
		productRepository.save(product);
	}
	
}
