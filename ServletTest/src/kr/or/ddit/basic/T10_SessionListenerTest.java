package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T10_SessionListenerTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("AAA", "AAA입니다.");
		req.getSession().setAttribute("AAA", "AAA수정합니다.");
		req.getSession().setAttribute("BBB", "BBB입니다.");
		req.getSession().removeAttribute("AAA");
		
		// HTTP세션 영역내에서 바인딩 관련 예제
		MySessionBindingListener bindingListener 
							= new MySessionBindingListener();
		req.getSession().setAttribute("obj", bindingListener);
		req.getSession().removeAttribute("obj");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
