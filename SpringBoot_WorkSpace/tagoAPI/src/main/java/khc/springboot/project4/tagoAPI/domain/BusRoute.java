package khc.springboot.project4.tagoAPI.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import khc.springboot.project4.tagoAPI.dto.BusRouteDto;
import lombok.Getter;

@Entity
@Getter
public class BusRoute {
	
	@Id
	@GeneratedValue
	private Long id; // DB에서 관리하는 ID
	
	private String routeid; // 노선ID
	private String nodeid; // 정류소ID
	private String citycode; // 도시코드
	private String nodenm;
	private String nodeno;
	private String nodeord;
	private String gpslati; 
	private String gpslong; 
	private String updowncd;
	
	public BusRoute() {
		
	}
	
	public BusRoute(BusRouteDto busRouteDto) {
		this.routeid = busRouteDto.getRouteid();
		this.nodeid = busRouteDto.getNodeid();
		this.citycode = busRouteDto.getCitycode();
		this.nodenm = busRouteDto.getNodenm();
		this.nodeno = busRouteDto.getNodeno();
		this.nodeord = busRouteDto.getNodeord();
		this.gpslati = busRouteDto.getGpslati();
		this.gpslong = busRouteDto.getGpslong();
		this.updowncd = busRouteDto.getUpdowncd();
		}
	
}
