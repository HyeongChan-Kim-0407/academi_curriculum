package Academy.Project.Rental.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

public class ReplyDto {

	@Getter @Setter
	public static class ReplyForm {
	    private String rcontent;
	    private MultipartFile[] rfiles;
	}
	
}
