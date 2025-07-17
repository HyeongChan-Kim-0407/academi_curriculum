package khc.springboot.project2.orders.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductForm {
	
	@NotBlank
	@Size(min = 3, message = "상품명은 필수 입력 사항입니다.")
	private String pname;
	
	@Min(value = 0, message = "최소 가격은 0원 이상이어야 합니다.")
	private int pprice;
	
	private String ptype;
	private String pinfo;
	
	
	
}
