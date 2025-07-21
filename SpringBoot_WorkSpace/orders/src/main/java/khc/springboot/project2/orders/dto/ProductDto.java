package khc.springboot.project2.orders.dto;

import java.util.ArrayList;
import java.util.List;

import khc.springboot.project2.orders.domain.OrderRequest;
import khc.springboot.project2.orders.domain.Product;
import khc.springboot.project2.orders.domain.ProductImage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductDto {
	
	private Long id;
	private String mid;
	private String pname; 
	private int pprice;
	private String ptype;
	private String pinfo;
	private List<String> pfilenameList = new ArrayList<>(); // 상품 이미지 파일명 목록
	private String pstate;
	private List<OrderRequestDto> odRequestDtoList = new ArrayList<>(); // 거래 신청 목록
	
	public ProductDto() {
		
	}
	
	public ProductDto(Product product) {
		this.id = product.getId();
		this.pname = product.getPname();
		this.pprice = product.getPprice();
		this.ptype = product.getPtype();
		this.pinfo = product.getPinfo();
		this.mid = product.getMember().getMid();
		this.pstate = product.getPstate();
		
		List<String> filenames = new ArrayList<>();
		for (ProductImage image : product.getImageList()) {
			filenames.add(image.getPfilename());
		}
		this.pfilenameList = filenames;
		
		for(OrderRequest orderRequest : product.getRequestList()) {
			OrderRequestDto ordto = new OrderRequestDto();
			ordto.setProductId(orderRequest.getProduct().getId());
			ordto.setMemberId(orderRequest.getMember().getId());
			ordto.setMid(orderRequest.getMember().getMid());
			ordto.setRequestState(orderRequest.getRequestState());
			odRequestDtoList.add(ordto);
		}
	}

}
