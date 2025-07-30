package Academy.Project.Rental.dto;



import java.util.ArrayList;
import java.util.List;

import Academy.Project.Rental.domain.Member;
import Academy.Project.Rental.domain.Place;
import Academy.Project.Rental.domain.Reply;
import Academy.Project.Rental.domain.ReplyImage;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyDto {

	private Long id;
	private Place place;	
	private Member member;		
	private String rcontent;	
	private String rdate;
	 private List<ReplyImage> imageList = new ArrayList<>();
	
	public ReplyDto() {
		
	}
	
	public ReplyDto(Reply reply) {
		this.id = reply.getId();
		this.place = reply.getPlace();		
		this.member = reply.getMember();
		this.rcontent = reply.getRcontent();
		this.rdate = reply.getRdate();
		this.imageList = reply.getImageList();
	}
}
