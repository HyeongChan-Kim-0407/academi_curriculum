package khc.springboot.project7.total.dto;

import khc.springboot.project7.total.domain.Location;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LocationDto {
	
	private String locationName;
	private String nx;
	private String ny;
	private String mid;
	
	public LocationDto() {
		
	}
	public LocationDto(Location location) {
		this.locationName = location.getLocationName();
		this.nx = location.getNx();
		this.ny = location.getNy();
		this.mid = location.getMember().getMid();
	}
	
}
