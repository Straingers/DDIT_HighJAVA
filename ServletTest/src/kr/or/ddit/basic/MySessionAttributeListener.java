package kr.or.ddit.basic;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class MySessionAttributeListener implements HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		System.out.println("[MySessionAttributeListener] "
						+ "attributeAdded() 호출됨 => " 
						+ hsbe.getName() + " : " + hsbe.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		System.out.println("[MySessionAttributeListener] "
				+ "attributeRemoved() 호출됨 => " 
				+ hsbe.getName() + " : " + hsbe.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent hsbe) {
		System.out.println("[MySessionAttributeListener] "
				+ "attributeReplaced() 호출됨 => " 
				+ hsbe.getName() + " : " + hsbe.getValue());
	}

}
