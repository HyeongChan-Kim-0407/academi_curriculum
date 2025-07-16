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
import org.springframework.web.bind.annotation.RequestParam;

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
		// Board(Entity) 목록을 변환할 Dto 목록 생성
		List<BoardDto> boardDtoList = new ArrayList<>();
		List<Board> boardList = boardsvc.getBoardList();
		// Board(Entity)를 Dto로 변환
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
	
	@GetMapping("/search")
	public String boardSearch(Model model, @RequestParam("searchWriter") String bwriter) {
		System.out.println("/board/search (get) - 글 작성자 검색 요청");
		System.out.println("검색할 작성자: " + bwriter);
		List<Board> boardList = boardsvc.getBoardListByBwriter(bwriter);
		List<BoardDto> boardDtoList = new ArrayList<>();
		for(Board board : boardList) {
			boardDtoList.add(new BoardDto(board));
		}
		model.addAttribute("boardList", boardDtoList);
		return "board/boardList";
	}
	
	@GetMapping("/del/{id}")
	public String boardDelete(@PathVariable("id") Long id) {
		System.out.println("/board/del (get) - 글 삭제 요청");
		System.out.println("삭제할 글 번호: " + id);
		boardsvc.deleteBoard(id);
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify/{id}")
	public String boardModifyForm(@PathVariable("id") Long id, BoardForm boardForm) {
		System.out.println("/board/modify (post) - 글 수정 요청");
		System.out.println("수정할 글 번호: " + id);
		System.out.println(boardForm);
		boardsvc.updateBoard(id, boardForm);
		
		return "redirect:/board/list";
	}
	
}
