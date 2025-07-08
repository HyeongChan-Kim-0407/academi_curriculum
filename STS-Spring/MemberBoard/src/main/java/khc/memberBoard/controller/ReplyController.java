package khc.memberBoard.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import khc.memberBoard.dto.Reply;
import khc.memberBoard.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService replysvc;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/getReplyList")
	@ResponseBody
	public ArrayList<Reply> getReplyList(int rbno){
		System.out.println("ajax - /getReplyList(get) 댓글 목록 요청");
		
		ArrayList<Reply> replyList = replysvc.findReplyListByBno(rbno); 
		
		return replyList;
	}
	
	@GetMapping("/registReply")
	@ResponseBody
	public String registReply(int rbno, String rcontents) {
		System.out.println("ajax - /registReply(get) 댓글 작성 요청");
		
		String loginId = (String) session.getAttribute("loginId");
		
		replysvc.registReply(loginId, rbno, rcontents);
		
		return null;
	}
	
}
