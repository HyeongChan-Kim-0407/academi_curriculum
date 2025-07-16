package khc.springboot.project1.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project1.board.domain.Board;
import khc.springboot.project1.board.dto.BoardDto;
import khc.springboot.project1.board.dto.BoardForm;
import khc.springboot.project1.board.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public void registBoard(Board board) {
		
		boardRepository.save(board);
		
	}

	public List<Board> getBoardList() {
//		return boardRepository.findAllByOrderByIdDesc();
		return boardRepository.findAll();
	}

	public Board getBoardByid(Long id) {
		return boardRepository.findById(id).orElse(null);
	}
	
	public List<Board> getBoardListByBwriter(String bwriter) {
		System.out.println("service - getBoardListByBwriter() 호출");
		List <Board> boardList = boardRepository.findByBwriter(bwriter);
		return boardList;
	}

	public void deleteBoard(Long id) {
		System.out.println("service- deleteBoard() 호출");
		boardRepository.deleteById(id);
	}
	
	public void updateBoard(Board board) {
		// board 엔티티
		boardRepository.save(board); // id가 있으면 update, 없으면 insert
	}

	public void updateBoard(Long id, BoardForm boardForm) {
		Board board = boardRepository.findById(id).orElse(null); // update 이전의 원본 board
		
		board.setBtitle(boardForm.getBtitle());
		board.setBwriter(boardForm.getBwriter());
		board.setBcontents(boardForm.getBcontents());
		boardRepository.save(board); // boardForm에서 갖고온 값으로 update
	}
}
