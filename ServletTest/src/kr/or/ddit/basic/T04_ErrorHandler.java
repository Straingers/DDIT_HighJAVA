package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 에러(예외)처리를 위한 서블릿 예제
 */
public class T04_ErrorHandler extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 예외 객체
		Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");
		
		// 에러 상태 코드
		Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
		
		// 에러 발생한 서블릿이름
		String servletName = (String) req.getAttribute("java.servlet.error.servlet_name");
		
		if(servletName == null) {
			servletName = "알수 없는 서블릿";
		}
		
		// 에러발생 url 정보
		String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
		
		if(requestUri == null) {
			requestUri = "알수 없는 URI";
		}
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		String title = "Error/Exception 정보";
		
		out.println("<html>\n"
				+ "<html>\n"
				+ "<head><title>" + title + "</title></head>\n"
				+ "<body>\n");
		
		if(throwable == null && statusCode == null) {
			out.println("<h2>에러정보 없음</h2>");
			out.println("홈페이지로 돌아가기: <a href=\""
					 + "http://localhost/ServletTest/"
					 + "\">홈페이지</a>");
		} else if(statusCode != null) {
			out.println("상태코드 : " + statusCode);
		} else {
			out.println("<h2>예외/에러 정보</h2>");
			out.println("서블릿 이름 : " + servletName + "<br><br>");
			out.println("예외 타입 : " + throwable.getClass().getName() + "<br><br>");
			out.println("요청 URI : " + requestUri + "<br><br>");
			out.println("예외 메시지 : " + throwable.getMessage());
		}
		out.println("</body>");
		out.println("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
