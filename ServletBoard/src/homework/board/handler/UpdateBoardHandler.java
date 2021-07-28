package homework.board.handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;
import homework.comm.handler.CommandHandler;

public class UpdateBoardHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/WEB-INF/view/board/updateBoard.jsp";
	
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
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));

		if(req.getMethod().equals("GET")) {
			
			IBoardService service = IBoardServiceImpl.getInstence();
			
			BoardVO board = service.readBoard(boardNo);
			
			req.setAttribute("bv", board);
			
			return VIEW_PAGE;
			
		} else {
			String boardWriter = req.getParameter("boardWriter");
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			String boardDate = req.getParameter("boardDate");
			
			IBoardService boardService = IBoardServiceImpl.getInstence();
			
			BoardVO bv = new BoardVO();
			bv.setBoardNo(boardNo);
			bv.setBoardWriter(boardWriter);
			bv.setBoardTitle(boardTitle);
			bv.setBoardContent(boardContent);
			bv.setBoardDate(boardDate);
			
			int cnt = boardService.updateBoard(bv);
			
			String msg = "";
			String result = "";
			if(cnt > 0) {
				msg = "성공";
				result = "게시글 수정 성공!";
			} else {
				msg = "실패";
				result = "게시글 수정 실패!";
			}
			
			String redirectUrl = req.getContextPath() + "/board/boardList.do?msg="
						+ URLEncoder.encode(msg, "UTF-8") + "&result="
						+ URLEncoder.encode(result, "UTF-8");
			
			return redirectUrl;
		}
		
	}

}
