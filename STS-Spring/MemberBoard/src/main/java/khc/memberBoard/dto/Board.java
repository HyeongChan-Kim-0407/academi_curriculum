package khc.memberBoard.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Board {
	
	private int bno;
	private String bwriter;
	private String btitle;
	private String bcontents;
	private int bhits;
	private String bdate;
	private String bfilename;
	private String bstate;
	
	private MultipartFile bfile;
	
	
}
