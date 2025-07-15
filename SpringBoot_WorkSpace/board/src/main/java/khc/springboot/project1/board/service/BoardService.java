package khc.springboot.project1.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.springboot.project1.board.domain.Board;
import khc.springboot.project1.board.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;
	
	public void registBoard(Board board) {
		
		boardRepository.save(board);
		
	}

	public List<Board> getBoardList() {
		return boardRepository.findOrderByIdDesc();
	}

	public Board getBoardByid(Long id) {
		return boardRepository.findById(id).orElse(null);
	}
	
}
