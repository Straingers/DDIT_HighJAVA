package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T08_ServletContextListenerTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().setAttribute("ATTR1", "속성1"); // 속성값 추가
		req.getServletContext().setAttribute("ATTR1", "속성11"); // 속성값 변경
		req.getServletContext().setAttribute("ATTR2", "속성2"); // 속성값 추가
		
		getServletContext().removeAttribute("ATTR1"); // 속성값 제거
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
