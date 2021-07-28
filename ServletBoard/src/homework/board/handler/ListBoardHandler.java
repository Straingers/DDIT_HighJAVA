package homework.board.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;
import homework.comm.handler.CommandHandler;

public class ListBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/WEB-INF/view/board/boardList.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		IBoardService service = IBoardServiceImpl.getInstence();
		
		List<BoardVO> boardList = service.getAllBoard();
		
		req.setAttribute("boardList", boardList);
		
		return VIEW_PAGE;
	}

}
