package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Filter를 이용한 한글 인코딩 처리 예제
 */
public class CustomEncoding implements Filter {
	
	private String encoding; // 인코딩 정보
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("현재 인코딩 설정 정보 : " + encoding);
		
		// 인코딩 설정하기
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		
		fc.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {

		if(fc.getInitParameter("encoding") == null) {
			this.encoding = "UTF-8";
		} else {
			this.encoding = fc.getInitParameter("encoding");
		}
	}
}
