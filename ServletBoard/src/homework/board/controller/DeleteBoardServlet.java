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

public class DeleteBoardServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/deleteBoard.jsp");
		
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
						+ URLEncoder.encode(result, "UTF-8")
						+ "&boardNo=" + boardNo;
		
		resp.sendRedirect(redirectUrl);
		
	}
}
