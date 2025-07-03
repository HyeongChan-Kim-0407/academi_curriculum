package khc.memberBoard.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import khc.memberBoard.dto.Board;
import khc.memberBoard.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardsvc;

	@Autowired
	private HttpSession session;

	@GetMapping("/boardList")
	public String boardListPage(Model model) {
		System.out.println("/boardList(get) 게시판 페이지 이동요청");
		
		/* Board 테이블의 글 목록 조회 */
		ArrayList<Board> boardList = boardsvc.findBoardList();
		/* 조회된 글 목록을 model에 저장 */
		model.addAttribute("boardList", boardList);
		/* model에 저장된 정보를 boardList 페이지와 같이 응답 */
		return "board/boardList";
	}

	@GetMapping("/boardWrite")
	public String boardWriteForm(RedirectAttributes ra) {
		System.out.println("/boardWirte(get) 글 작성 페이지 이동요청");
		if (session.getAttribute("loginId") == null) {
			System.out.println("잘못된 접근");
			ra.addFlashAttribute("msg", "잘못된 접근입니다. 로그인 후 이용해주세요.");
			return "redirect:/login";
		}

		return "board/boardWriteForm";
	}

	@PostMapping("/boardWrite")
	public String boardWrite(Board board, RedirectAttributes ra) {
		System.out.println("/boardWrite(post) 글 등록 요청");
		/* 세션에 로그인 아이디 확인 */
		if (session.getAttribute("loginId") == null) {
			/* 로그인이 안돼있다면 로그인화면으로 이동 */
			System.out.println("잘못된 접근 : 로그인 안됨");
			return "redirect:/";
		}
		/* 로그인 상태면 세션의 로그인 아이디를 board 객체의 bwriter로 설정 */
		String loginId = (String) session.getAttribute("loginId");
		board.setBwriter(loginId);
		System.out.println(board);
		/* 서비스의 글 등록 기능 호출 및 결과 확인 */
		try {
			boardsvc.boardRegist(board);
			System.out.println("등록 완료");
			ra.addFlashAttribute("boardMsg", "새 글이 등록 되었습니다. \\n게시판 페이지로 이동합니다.");
		} catch (Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
			System.out.println(e.getMessage());
			ra.addFlashAttribute("boardMsg", "글 등록에 실패하였습니다. \\n게시판 페이지로 이동합니다.");
		}
		/* 글 등록 결과에 따라 글 목록 페이지로 이동 요청 및 결과 메시지 응답 */
		return "redirect:/boardList";		
	}

}
