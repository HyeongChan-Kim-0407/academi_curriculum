package khc.arranged.dependency.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class EntityClass {
	
	@Id
	@GeneratedValue
	private Long id;
	
	// 컬럼 정의
	private String name; // 이름 컬럼
	
	private String email; // 이메일 컬럼
	
	public EntityClass() {
		
	}
	
}
