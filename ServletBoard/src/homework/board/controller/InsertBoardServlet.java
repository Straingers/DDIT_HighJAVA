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

public class InsertBoardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/insertBoard.jsp");
		
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		
		resp.sendRedirect(redirectUrl);
	}
}
