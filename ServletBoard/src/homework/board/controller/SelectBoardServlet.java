package homework.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;

public class SelectBoardServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String boardNo = req.getParameter("boardNo");
		
		IBoardService boardService = IBoardServiceImpl.getInstence();
		
		BoardVO board = 
				boardService.readBoard(Integer.parseInt(boardNo));
		
		req.setAttribute("bv", board);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/board.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
