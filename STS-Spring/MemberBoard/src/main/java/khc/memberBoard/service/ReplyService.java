package khc.memberBoard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import khc.memberBoard.dao.ReplyDao;
import khc.memberBoard.dto.Reply;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyDao replyDao;

	public ArrayList<Reply> findReplyListByBno(int rbno) {
		System.out.println("ReplyService - findReplyListByBno() 호출");
		ArrayList<Reply> replyList = replyDao.selectReplyListByBno(rbno);
		
		return replyList;
	}

	public void registReply(String loginId, int rbno, String rcontents) {
		System.out.println("ReplyService - registReply() 호출");
		replyDao.insertReply(loginId, rbno, rcontents);
	}
	
}
