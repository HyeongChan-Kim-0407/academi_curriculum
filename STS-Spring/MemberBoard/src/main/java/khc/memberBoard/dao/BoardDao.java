package khc.memberBoard.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import khc.memberBoard.dto.Board;

public interface BoardDao {

	void insertBoard(Board board);

	ArrayList<Board> selectBoardList();

	int updateBhitsByBno(int bno);
	
	Board selectBoardByBno(int bno);

	ArrayList<Board> searchBoardByTypeAndText(@Param("searchType") String searchType, @Param("searchText") String searchText);

	int updateBoard(Board modifyBoard);

	int updateBoardState(int bno);

	ArrayList<Board> selectBoardListByBwriter(String loginId);

}
