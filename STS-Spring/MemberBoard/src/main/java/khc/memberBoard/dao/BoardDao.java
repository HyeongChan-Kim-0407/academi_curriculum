package khc.memberBoard.dao;

import java.util.ArrayList;

import khc.memberBoard.dto.Board;

public interface BoardDao {

	void insertBoard(Board board);

	ArrayList<Board> selectBoardList();

}
