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

public class SelectBoardAllServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IBoardService boardService = IBoardServiceImpl.getInstence();
		
		List<BoardVO> boardList = boardService.getAllBoard();
		
		req.setAttribute("boardList", boardList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/boardList.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}