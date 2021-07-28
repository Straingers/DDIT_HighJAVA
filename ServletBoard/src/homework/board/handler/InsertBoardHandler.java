package homework.board.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;
import homework.comm.handler.CommandHandler;

public class InsertBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/WEB-INF/view/board/insertBoard.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		} else {
			// 요청 파라미터 가져오기
			String boardTitle = req.getParameter("boardTitle");
			String boardWriter = req.getParameter("boardWriter");
			String boardContent = req.getParameter("boardContent");
			
			// 서비스 객체 생성
			IBoardService boardService = IBoardServiceImpl.getInstence();
			
			// 게시글 등록하기
			BoardVO bv = new BoardVO();
			bv.setBoardTitle(boardTitle);
			bv.setBoardWriter(boardWriter);
			bv.setBoardContent(boardContent);
			
			int cnt = boardService.insertBoard(bv);
			
			String msg = "";
			String result = "";
			if(cnt > 0) {
				msg = "성공";
				result = "게시글 등록 성공!";
			} else {
				msg = "실패";
				result = "게시글 등록 실패!";
			}
			
			// 게시글 목록으로 다시 이동
			String redirectUrl = req.getContextPath() + "/board/boardList.do?msg=" 
							+ URLEncoder.encode(msg, "UTF-8") + "&result="
							+ URLEncoder.encode(result, "UTF-8");
			
			return redirectUrl;
		}
	}
}
