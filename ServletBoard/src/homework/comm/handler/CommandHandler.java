package homework.comm.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	
	/**
	 * 리다이렉트를 결정하는 메서드
	 * @param req
	 * @return
	 */
	public boolean isRedirect(HttpServletRequest req);
	
	/**
	 * 요청한 내용을 처리하는 메서드
	 * @param req
	 * @param resp
	 * @return 뷰 페이지 정보
	 * @throws Exception
	 */
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
