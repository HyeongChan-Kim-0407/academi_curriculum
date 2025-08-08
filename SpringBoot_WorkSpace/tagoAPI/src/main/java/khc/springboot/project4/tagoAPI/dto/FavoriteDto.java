package khc.springboot.project4.tagoAPI.dto;

import khc.springboot.project4.tagoAPI.domain.Favorite;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FavoriteDto {
	
	private String citycode; // 도시 코드
	
	private String nodeid;
	
	private String nodenm;
	
	private String gpslati;
	
	private String gpslong;
	
	private String kakaoid;
	
	public FavoriteDto() {
		// 기본 생성자
	}
	
	public FavoriteDto(Favorite favorite) {
		this.citycode = favorite.getCitycode();
		this.nodeid = favorite.getNodeid();
		this.nodenm = favorite.getNodenm();
		this.gpslati = favorite.getGpslati();
		this.gpslong = favorite.getGpslong();
		this.kakaoid = favorite.getKakaoid();
	}
	
}
