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
		if(loginId == null) {
			String afterURL = "/boardView?bno="+rbno;
			session.setAttribute("afterURL", afterURL);
			return "L";
		}
		try {
			replysvc.registReply(loginId, rbno, rcontents);
			return "Y";
		} catch (Exception e) {
			return "N";
		}
	}
	
	@GetMapping("/deleteReply")
	@ResponseBody
	public String deleteReply(int rno) {
		System.out.println("/replyDelete(get) 댓글 삭제 요청");
		
		String loginId = (String) session.getAttribute("loginId");
		Reply reply = replysvc.findReplyByrno(rno); 
		int rbno = reply.getRbno();
		if(loginId == null) {
			System.out.println("로그인 필요");
			String afterURL = "/boardView?bno="+rbno;
			session.setAttribute("afterURL", afterURL);
			return "L";
		}
		
		int result = replysvc.deleteReply(rno);
		
		if(result != 1) {
			System.out.println("삭제 과정 중 오류 발생");
			return "N";
		}
		
		
		return "Y";
	}
	
}
