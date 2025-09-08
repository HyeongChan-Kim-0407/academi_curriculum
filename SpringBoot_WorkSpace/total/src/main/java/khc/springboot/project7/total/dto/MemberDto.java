package khc.springboot.project7.total.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import khc.springboot.project7.total.domain.Location;
import khc.springboot.project7.total.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MemberDto {
	
	private Long id;
	private String mid;
	private String mpw;
	private String mname;
	private String memail;
	private LocalDate mjoindate;
	private String mroute;
	private List<LocationDto> locations;
	
	public MemberDto() {
		
	}
	
	public MemberDto(Member member) {
		this.id = member.getId();
		this.mid = member.getMid();
		this.mpw = member.getMpw();
		this.mname = member.getMname();
		this.memail = member.getMemail();
		this.mjoindate = member.getMjoindate();
		this.mroute = member.getMroute();
		List<LocationDto> dtoList = new ArrayList<>();
		for(Location location : member.getLocations()) {
			LocationDto dto = new LocationDto(location);
			dtoList.add(dto);
		}
		this.locations = dtoList;
	}
	
}
