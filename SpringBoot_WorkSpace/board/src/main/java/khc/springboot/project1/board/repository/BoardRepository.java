package khc.springboot.project1.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import khc.springboot.project1.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

	List<Board> findOrderByIdDesc();
	
}
