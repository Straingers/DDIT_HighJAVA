package homework.board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;
import homework.comm.handler.CommandHandler;

public class BoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/WEB-INF/view/board/board.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String boardNo = req.getParameter("boardNo");
		
		IBoardService boardService = IBoardServiceImpl.getInstence();
		
		BoardVO board = 
				boardService.readBoard(Integer.parseInt(boardNo));
		
		req.setAttribute("board", board);
		
		return VIEW_PAGE;
	}

}