package khc.springboot.project1.board.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import khc.springboot.project1.board.dto.BoardForm;
import lombok.Getter;
import lombok.Setter; 

@Entity
@Table(name="testboard")
@Getter @Setter
public class Board {
	
	@Id
	@GeneratedValue
	private Long id; // 글 번호(시퀀스를 통한 자동 증가)
	
	@Column(nullable = false, length = 500)
	private String btitle;
	
	private String bwriter;
	
	private String bcontents;
	
	private LocalDateTime bdate;
	
	public Board() {
		
	}
	
	public Board(BoardForm boardForm) {
		this.btitle = boardForm.getBtitle();
		this.bwriter = boardForm.getBwriter();
		this.bcontents = boardForm.getBcontents();
		this.bdate = LocalDateTime.now();
	}
	
}
