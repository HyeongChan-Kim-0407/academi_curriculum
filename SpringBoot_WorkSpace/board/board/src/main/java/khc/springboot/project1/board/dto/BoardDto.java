package khc.springboot.project1.board.dto;

import java.time.LocalDateTime;

import khc.springboot.project1.board.domain.Board;
import lombok.Getter;

@Getter
public class BoardDto {

	private Long id; // 글 번호(시퀀스를 통한 자동 증가)
	private String btitle;
	private String bwriter;
	private String bcontents;
	private LocalDateTime bdate;
	
	public BoardDto(Board board) {
		this.id = board.getId();
		this.btitle = board.getBtitle();
		this.bwriter = board.getBwriter();
		this.bcontents = board.getBcontents();
		this.bdate = board.getBdate();
	}
	
}
