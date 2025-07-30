package Academy.Project.Rental.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyForm {

	private String rcontent;
	private MultipartFile[] rfiles;

}
