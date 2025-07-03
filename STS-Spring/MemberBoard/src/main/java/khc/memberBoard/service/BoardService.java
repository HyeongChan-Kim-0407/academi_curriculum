package khc.memberBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import khc.memberBoard.dao.BoardDao;
import khc.memberBoard.dto.Board;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private ServletContext servletContext;
	
	/* 글 등록 기능 */
	public void boardRegist(Board board) throws IllegalStateException, IOException {
		System.out.println("BoardService - boardRegister() 호출");
		/* 새 글 번호 조회(SELECT NVL(MAX(BNO, 0) + 1 FROM BOARD) */
		/* int bno = boardDao.selectNewBno(); */
		
		/* 오후에 할 것 글 작성 시 파일 업로드 기능 추가 */
		/* 파일 유무 확인 */
		/* 파일이 있는 경우 
		 // 1. 업로드한 파일 저장 경로 설정
		 // 2. 파일 이름 재설정 (중복 처리 ex)덮어쓰기 방지 용도)
		 // 3. 파일 저장
		 // 4. board 객체의 bfilename 필드에 저장
		 
		 */
		
		MultipartFile bfile = board.getBfile();
		if(!bfile.isEmpty()) { // 업로드한 파일이 있을 때 파일 저장 경로와 저장할 새 파일명 생성
			String path = servletContext.getRealPath("resources/boardFileUpload");
			System.out.println(path); // 파일을 저장할 경로
			// 저장할 파일명 생성
			// 원본파일명
			String originalFileName = bfile.getOriginalFilename();
			// 저장할 파일명
			String newFileName = UUID.randomUUID().toString(); // + 확장자 
			// 원본 파일의 확장자
			// 원본 파일에서 가장 마지막 "."문자의 인덱스 번호
			int suffixIndex = originalFileName.lastIndexOf("."); // 확장자가 포함된 원본 파일명
			String suffix = originalFileName.substring(suffixIndex); // 인덱스 번호 이후의 문자열을 추출 (확장자)
			String bfilename = newFileName + suffix; // 저장할 파일명 + 확장자
			System.out.println("bfilename : " + bfilename);
			
			File file = new File(path, bfilename);
			bfile.transferTo(file);
			board.setBfilename(bfilename);
		}
		
		boardDao.insertBoard(board);
	
	}

	public ArrayList<Board> findBoardList() {
		
		ArrayList<Board> boardList = boardDao.selectBoardList();
		
		return boardList;
	}
	
	
	/* 글 조회 기능 */
	/* 글 목록 조회 기능 */
	
}
