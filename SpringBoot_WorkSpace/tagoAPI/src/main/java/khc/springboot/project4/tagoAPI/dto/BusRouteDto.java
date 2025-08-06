package khc.springboot.project4.tagoAPI.dto;

import khc.springboot.project4.tagoAPI.domain.BusRoute;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BusRouteDto {
	
	private String citycode; // 도시코드
	private String routeid;	// 노선ID
	private String nodeid; 	// 정류소ID
	private String nodenm;
	private String nodeno;
	private String nodeord;
	private String gpslati; 
	private String gpslong; 
	private String updowncd;
	
	private boolean isTrue; // 버스가 해당 정류소에 정차해있는지 여부
	
	public BusRouteDto() {
		
	}
	
	public BusRouteDto(BusRoute busRoute) {
		this.citycode = busRoute.getCitycode();
		this.routeid = busRoute.getRouteid();
		this.nodeid = busRoute.getNodeid();
		this.nodenm = busRoute.getNodenm();
		this.nodeno = busRoute.getNodeno();
		this.nodeord = busRoute.getNodeord();
		this.gpslati = busRoute.getGpslati();
		this.gpslong = busRoute.getGpslong();
		this.updowncd = busRoute.getUpdowncd();
		this.isTrue = false; // 초기값은 false로 설정
		}
	
}
