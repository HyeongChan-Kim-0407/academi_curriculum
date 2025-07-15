package khc.springboot.project1.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardForm {
	
	private String btitle;
	private String bwriter;
	private String bcontents;
	
}
