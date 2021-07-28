package kr.or.ddit.basic;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener 
											, ServletContextAttributeListener {

	// 생성자
	public MyServletContextListener() {
		System.out.println("[MyServletContextListener] "
		 				+ "MyServletContextListener 생성자 호출됨.");
	}
	
	/**
	 * 서블릿 컨텍스트가 제거되었을 때 호출됨.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] "
				+ "contextDestroyed() 호출됨.");
	}

	/**
	 * 서블릿 컨텍스트가 초기화 되었을때 호출됨.
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener] "
				+ "contextInitialized() 호출됨.");
	}

	/**
	 * 서블릿 컨텍스트에 속성이 추가되었을때 호출됨.
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] "
				+ "attributeAdded() 호출됨. => "
				+ scae.getName());
	}

	/**
	 * 서블릿 컨텍스트에 속성이 삭제되었을때 호출됨.
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] "
				+ "attributeRemoved() 호출됨. => "
				+ scae.getName());
	}

	/**
	 * 서블릿 컨텍스트에 속성이 변경되었을때 호출됨.
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		System.out.println("[MyServletContextListener] "
				+ "attributeReplaced() 호출됨. => "
				+ scae.getName());
	}
}
