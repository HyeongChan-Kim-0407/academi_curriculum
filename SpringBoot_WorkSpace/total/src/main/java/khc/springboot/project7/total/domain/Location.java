package khc.springboot.project7.total.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import khc.springboot.project7.total.dto.LocationDto;
import lombok.Getter;

@Entity
@Getter
public class Location {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String locationName;
	private String nx;
	private String ny;
	
	@ManyToOne
	@JoinColumn(name = "memberId")
	private Member member;
	
	public Location() {
		
	}
	
	public Location(LocationDto locationDto, Member member) {
		this.locationName = locationDto.getLocationName();
		this.nx = locationDto.getNx();
		this.ny = locationDto.getNy();
		this.member = member;
	}
	
}
