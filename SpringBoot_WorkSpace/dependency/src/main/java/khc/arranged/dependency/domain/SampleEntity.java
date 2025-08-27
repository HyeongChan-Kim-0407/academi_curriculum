package khc.arranged.dependency.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class SampleEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	private String userpass;
	
	public SampleEntity() {
		
	}
	
	public SampleEntity(String username, String userpass) {
		this.username = username;
		this.userpass = userpass;
	}
	
}
