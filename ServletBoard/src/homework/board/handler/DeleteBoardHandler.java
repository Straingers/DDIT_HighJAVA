package homework.board.handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;
import homework.comm.handler.CommandHandler;

public class DeleteBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/WEB-INF/view/board/deleteBoard.jsp";
	
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
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			
			req.setAttribute("boardNo", boardNo);
			
			return VIEW_PAGE;
		} else {
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			
			IBoardService boardService = IBoardServiceImpl.getInstence();
			
			int cnt = boardService.deleteBoard(boardNo);
			
			String msg = "";
			String result = "";
			if(cnt > 0) {
				msg = "성공";
				result = "게시글 삭제 성공!";
			} else {
				msg = "실패";
				result = "게시글 삭제 실패";
			}
			
			// 게시글 목록으로 다시 이동
			String redirectUrl = req.getContextPath() + "/board/boardList.do?msg=" 
					+ URLEncoder.encode(msg, "UTF-8") + "&result="
					+ URLEncoder.encode(result, "UTF-8");
			
			return redirectUrl;
		}
	}
}
