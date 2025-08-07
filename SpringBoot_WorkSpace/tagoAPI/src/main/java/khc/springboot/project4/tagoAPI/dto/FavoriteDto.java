package khc.springboot.project4.tagoAPI.dto;

import khc.springboot.project4.tagoAPI.domain.Favorite;

public class FavoriteDto {
	
	private String citycode; // 도시 코드
	
	private String nodeid;
	
	private String gpslati;
	
	private String gpslong;
	
	private String kakaoid;
	
	public FavoriteDto() {
		// 기본 생성자
	}
	
	public FavoriteDto(Favorite favorite) {
		this.citycode = favorite.getCitycode();
		this.nodeid = favorite.getNodeid();
		this.gpslati = favorite.getGpslati();
		this.gpslong = favorite.getGpslong();
		this.kakaoid = favorite.getKakaoid();
	}
	
}
