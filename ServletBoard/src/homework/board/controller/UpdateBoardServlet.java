package homework.board.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;

public class UpdateBoardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/updateBoard.jsp");
		
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String boardNo = req.getParameter("boardNo");
		String boardWriter = req.getParameter("boardWriter");
		String boardTitle = req.getParameter("boardTitle");
		String boardContent = req.getParameter("boardContent");
		String boardDate = req.getParameter("boardDate");
		
		IBoardService boardService = IBoardServiceImpl.getInstence();
		
		BoardVO bv = new BoardVO();
		bv.setBoardWriter(boardWriter);
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardDate(boardDate);
		
		int cnt = boardService.updateBoard(bv);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		
		// 수정 한 글로 다시 이동
		String redirectUrl = req.getContextPath() + "/board/board?msg=&"
					+ URLEncoder.encode(msg, "UTF-8")
					+ "&boardNo=" + boardNo;
		
		resp.sendRedirect(redirectUrl);
	}
}
