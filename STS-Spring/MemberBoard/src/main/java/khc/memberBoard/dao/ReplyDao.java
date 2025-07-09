package khc.memberBoard.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import khc.memberBoard.dto.Reply;

public interface ReplyDao {

	ArrayList<Reply> selectReplyListByBno(int rbno);

	void insertReply(@Param("rwriter") String loginId, @Param("rbno") int rbno, @Param("rcontents") String rcontents);

	int updateRstateByRno(int rno);

	Reply selectReplyByRno(int rno);

}
