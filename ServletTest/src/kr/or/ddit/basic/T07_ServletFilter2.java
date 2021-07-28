package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T07_ServletFilter2 implements Filter {

	@Override
	public void destroy() {
		System.out.println("T07_ServletFilter2 => destroy() 호출됨.");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("T07_ServletFilter2 시작.");
		
		// 서블릿 수행시간 계산하기
		long startTime = System.currentTimeMillis();
		
		// 필터체인을 실행한다.(req, resp 객체 전달)
		fc.doFilter(req, resp);
		
		System.out.println("수행시간(ms) : " 
					+ (System.currentTimeMillis() - startTime));
		
		System.out.println("T07_ServletFilter2 완료.");
		
	}

	@Override
	public void init(FilterConfig fc) throws ServletException {
		System.out.println("T07_ServletFilter2 => init() 호출됨.");
		
	}

}
