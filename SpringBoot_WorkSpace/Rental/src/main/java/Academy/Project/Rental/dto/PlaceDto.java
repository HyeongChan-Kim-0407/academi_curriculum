package Academy.Project.Rental.dto;

import java.util.ArrayList;
import java.util.List;

import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.PlaceImage;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlaceDto {
	
	private Long id;
	private String mid;
	private String ptitle; 
	private String pinfo;
	private String ptype;
	private String plocation;
	private	int pprice;
	private List<String> pfilenameList = new ArrayList<>(); // 장소 이미지 파일명 목록
	
	public PlaceDto() {
		
	}
	
	public PlaceDto(Place place) {
		this.id = place.getId();
		this.mid = place.getPmember().getMid();
		this.ptitle = place.getPtitle();
		this.pinfo = place.getPinfo();
		this.ptype = place.getPtype();
		this.plocation = place.getPlocation();
		this.pprice = place.getPprice();
		
		List<String> filenames = new ArrayList<>();
		for (PlaceImage image : place.getImageList()) {
			filenames.add(image.getPfilename());
		}
		this.pfilenameList = filenames;
	}
	
}