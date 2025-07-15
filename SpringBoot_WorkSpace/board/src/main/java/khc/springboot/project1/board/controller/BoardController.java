package khc.springboot.project1.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import khc.springboot.project1.board.domain.Board;
import khc.springboot.project1.board.dto.BoardDto;
import khc.springboot.project1.board.dto.BoardForm;
import khc.springboot.project1.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardsvc;
	
	@GetMapping("/list")
	public String boardList(Model model) {
		System.out.println("/board/list - 글 목록 페이지 이동 요청");
		// 글 목록 조회
		List<Board> boardList = boardsvc.getBoardList();
		// Board(Entity)를 Dto로 변환	
		List<BoardDto> boardDtoList = new ArrayList<>();
		for(Board board : boardList) {
			boardDtoList.add(new BoardDto(board));
		}
		// 글 목록을 Model에 저장
		model.addAttribute("boardList", boardDtoList);
		return "board/boardList";
	}
	
	@GetMapping("/write")
	public String boardWriteForm() {
		System.out.println("/board/write - 글 작성 페이지 이동 요청");
		return "board/boardWrite";
	}
	
	@PostMapping("/write")
	public String boardWrite(BoardForm boardForm) {
		System.out.println("/board/write(post) - 글 등록 요청");
		System.out.println(boardForm);
		// boardForm > board(Entity) 변환
		Board board = new Board(boardForm);
		// 글 등록 처리 기능 호출
		try {
		boardsvc.registBoard(board);
		return "redirect:/board/list"; // 글 등록 성공. 글 목록 페이지로 이동 요청
		} catch(Exception e) {
			return "redirect:/"; // 글 등록 실패. 메인 페이지로 이동 요청
		}
		
	}
//	http://localhost:8080/board/view/1
	@GetMapping("/view/{id}")
	public String boardViews(@PathVariable("id") Long id, Model model) {
		System.out.println("/board/view(get) - 글 상세페이지 이동 요청");
		System.out.println("조회할 글 번호: " + id);
		Board board = boardsvc.getBoardByid(id);
		if(board != null) {
			BoardDto boardDto = new BoardDto(board);
			model.addAttribute("board", boardDto);
			return "board/boardView"; // 글 상세 페이지로 이동
		} else {
			return "redirect:/board/list"; // 글 상세 페이지로 이동
		}
	}
	
	
}
