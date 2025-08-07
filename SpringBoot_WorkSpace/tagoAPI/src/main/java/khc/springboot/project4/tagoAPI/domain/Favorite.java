package khc.springboot.project4.tagoAPI.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Favorite {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String citycode; // 도시 코드
	
	private String nodeid;
	
	private String gpslati;
	
	private String gpslong;
	
	private String kakaoid;
	
	public Favorite() {
		// 기본 생성자
	}
	
	public Favorite(String citycode, String nodeid, String gpslati, String gpslong, String kakaoid) {
		this.citycode = citycode;
		this.nodeid = nodeid;
		this.gpslati = gpslati;
		this.gpslong = gpslong;
		this.kakaoid = kakaoid;
	}	
}
