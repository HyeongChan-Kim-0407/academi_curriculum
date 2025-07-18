package khc.springboot.project2.orders.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import khc.springboot.project2.orders.domain.Member;
import khc.springboot.project2.orders.domain.Product;
import khc.springboot.project2.orders.domain.ProductImage;
import khc.springboot.project2.orders.dto.ProductDto;
import khc.springboot.project2.orders.dto.ProductForm;
import khc.springboot.project2.orders.repository.MemberRepository;
import khc.springboot.project2.orders.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	private final String PFILEPATH = "C:/productImages";
	
	/* 상품 등록기능 */
	public void registerProduct(ProductForm productForm, String loginId) throws IllegalStateException, IOException {
		// 로그인 된 회원의 아이디로 MemberRepository에서 Member Entity를 조회
		Member loginMember = memberRepository.findByMid(loginId);
		// ProductForm과 Member Entity를 Product Entity로 변환
		Product product = new Product(productForm, loginMember);
		// productForm에 있는 MultipartFile 타입의 필드를 Product Entity의 List<ProductImage> 타입의 필드로 변환 : 난수 파일명 및 경로명 생성
		MultipartFile[] pfiles = productForm.getPfiles();
		for ( MultipartFile pfile : pfiles) {
			if(!pfile.isEmpty()) { // 업로드 한 파일이 있는 경우
				// 파일을 업로드할 폴더에 저장 (외부 폴더 C:/productImages)
				String originalFileName = pfile.getOriginalFilename(); // 원본 파일명
				int suffixIndex = originalFileName.lastIndexOf("."); // 확장자가 시작되는 인덱스
				String suffix = originalFileName.substring(suffixIndex); // suffixIndex 이후의 파일명 추출 = 확장자
				String randomFileName = UUID.randomUUID().toString(); // 난수 파일명 생성
				String pfileName = randomFileName + suffix; // 난수 파일명 + 확장자 = 최종 파일명
				
				File file = new File(PFILEPATH, pfileName);
				pfile.transferTo(file); // 파일 업로드
				
				// 파일을 불러올 때 사용할 경로 생성
				String pathName = "/productImage/" + pfileName;
				// ProductImage Entity 생성
				ProductImage productImage = new ProductImage(pathName, product);
				// Product Entity의 ImageList에 ProductImage Entity 추가
				product.getImageList().add(productImage);
			}
		}
		// 변환된 Product Entity를 Product Repository에 저장(save)
		productRepository.save(product);
	}

	public List<ProductDto> getProductList() {
		System.out.println("ProductService - getProductList() 호출");
		
		List<Product> productList = productRepository.findAll();
		
		if(productList == null) {
			System.out.println("등록된 상품이 없습니다.");
			return null; // 등록된 상품이 없으면 null 반환
		}
		
		List<ProductDto> productDtoList = new ArrayList<>();
		for (Product product : productList) {
			ProductDto productDto = new ProductDto(product);
			productDtoList.add(productDto);
		}
		
		return productDtoList;
	}

	public ProductDto getProductById(Long id) {
		System.out.println("ProductService - getProductById() 호출");
		
		Product product = productRepository.findById(id).orElse(null);
		
		ProductDto productDto = new ProductDto(product);
		
		return productDto;
	}
	
}
