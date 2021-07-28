package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class T05_ServletCookieTest extends HttpServlet {
/**
     쿠키 정보를 다루기 위한 예제
  (웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 상태를 유지함.)
   1. 구성 요소
    - 이름
    - 값
    - 유효시간(초)
    - 도메인 : ex) www.somehost.com, .somehost.com
       => 쿠키의 도메인이 쿠키를 생성한 서버의 도메인을 벗어나면 브라우저는 쿠키를 저장(생성)하지 않는다.
          (보안 문제 때문에)
   2. 동작 방식
    - 쿠키 생성단계 : 생성한 쿠키를 응답데이터의 헤더에 저장하여 웹브라우저에 전송한다.
    - 쿠키 저장단계 : 브라우저는 응답데이터에 포함된 쿠키를 쿠키저장소에 보관한다.
                 (쿠키종류에 따라 메모리나 파일에 저장함.)
    - 쿠키 전송단계 : 웹브라우저는 저장한 쿠키를 요청이 있을때마다 웹서버에 전송한다.
                 (삭제되기 전까지...)
                                  웹서버는 브라우저가 전송한 쿠키를 사용해서 필요한 작업을 수행한다.
 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		setCookieExam(req, resp); // 쿠키 설정 예제
		
//		readCookieExam(req, resp); // 쿠키정보 읽기 예제
		
		deleteCookieExam(req, resp); // 쿠키정보 삭제 예제
		
	}
	
	private void deleteCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	/**
	     사용중인 쿠키를 삭제하는 방법...
	     
	   1. 사용중인 쿠키정보를 이용하여 쿠키객체를 생성한다.
	   2. 쿠키 객체의 최대지속시간을 0으로 설정한다.
	   3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.
	 */
		Cookie[] cookies = req.getCookies();
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 삭제 예제";
		
		out.println("<html><head><title>" + title + "</title></head>"
				 + "<body>");
		
		if(cookies != null) {
			out.println("<h2 align=\"center\">" + title + "</h2>");
			
			for(Cookie cookie : cookies) {
				if((cookie.getName()).equals("userId")){
					// 쿠키 제거하기
					cookie.setMaxAge(0);
					
					resp.addCookie(cookie);
					out.println("삭제한 쿠키 : " + cookie.getName() + "<br>");
				}
				out.print("쿠키 이름 : " + cookie.getName() + " / ");
				out.print("쿠키 값 : " + URLDecoder.decode(cookie.getValue(), "utf-8") + "<br>");
			}
		} else {
			out.println("<h2>쿠키 정보가 없습니다.</h2>");
		}
		out.println("</body></html>");
	}

	@SuppressWarnings("unused")
	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Cookie cookie = null;
		
		// 현재 도메인에서 사용중인 쿠키정보 배열 가져오기
		Cookie[] cookies = req.getCookies();
		
		// 응답헤더에 인코딩 및 Content-type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();

		String title = "쿠키 정보 읽기 예제";
		
		out.println("<html><head><title>"
					+ title + "</title></head>"
					+ "<body>");
		if(cookies != null) {
			out.println("<h2 align=\"center\">" + title + "</h2>");
			
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				out.println("name : " + cookie.getName() + "<br>");
				out.println("value : " + URLDecoder.decode(cookie.getValue(), "utf-8") + "<br>");
				out.println("<hr>");
			}
		} else {
			out.println("<h2>쿠키정보가 없습니다.</h2>");
		}
		out.println("</body></html>");
	}

	@SuppressWarnings("unused")
	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	/**
	     쿠키 정보를 설정하는 방법...
	   
	   1. 쿠키객체를 생성한다. 사용불가 문자(공백, []()=,"/?@:;)
	      Cookie cookie = new Cookie("키값", "value값");
	     => 이외 의 값(예를 들면 한글)을 사용시에는 URLEncoder.encode() 사용하여 인코딩 처리를 해준다.
	     
	   2. 쿠키 최대 지속시간을 설정한다.(초단위)
	     => 지정하지 않으면 웹브라우저를 종료할때 쿠키를 함께 삭제한다.
	     cookie.setMaxAge(60 * 60 * 24); // 24시간
	     
	   3. 응답헤더에 쿠키 객체를 추가한다. 
	     response.addCookie(cookie);
	     
	   => 출력버퍼가 플러시된 이후에는 쿠키를 추가할 수 없다.
	     (응답헤더를 통해서 웹브라우저에 전달하기 때문에...)
	 */
		// 쿠키 생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));
		
		// 쿠키값에 한글을 사용시 인코딩 처리를 해준다.
		Cookie name = 
				new Cookie("name", URLEncoder.encode(
						req.getParameter("name"), "utf-8"));
		
		// 쿠키 소멸시간 설정(초단위) => 지정하지 않으면 웹브라우저를 종료할때 쿠키를 함께 삭제한다.
		userId.setMaxAge(60 * 60 * 24); // 1일
		name.setMaxAge(60 * 60 * 48); // 2일

		// 쿠키 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);
		
		// 응답헤더에 인코딩 및 content-type설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키설정 예제";
		
		out.println("<html><head><title>"
					+ title + "</title></head>");
		out.println("<body>"
					+ "<h1 align=\"center\">" + title + "</h1>"
					+ "<ul>"
					+ "<li><b>ID</b>: " + req.getParameter("userId") + "</li>"
					+ "<li><b>이름</b>: "+ req.getParameter("name") + "</li>"
					+ "</ul></body></html>"
					);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
