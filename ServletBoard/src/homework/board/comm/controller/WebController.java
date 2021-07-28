package homework.board.comm.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homework.comm.handler.CommandHandler;
import homework.comm.handler.NullHandler;

public class WebController extends HttpServlet {

	// 핸들러 객체 저장용 맵 객체
	Map<String, CommandHandler> cmmHandlerMap = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		String configFileRealPath = 
					config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		
		try {
			fr = new FileReader(configFileRealPath);
			handlerProp.load(fr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(Object key : handlerProp.keySet()) {
			String command = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(command));
				
				CommandHandler handler = (CommandHandler) klass.newInstance();
				cmmHandlerMap.put(command, handler);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	/**
	 * 요청 처리 메서드
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String reqURI = req.getRequestURI();
		
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		
		CommandHandler handler = cmmHandlerMap.get(reqURI);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = "";
		
		try {
			viewPage = handler.process(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(viewPage != null) {
			if(handler.isRedirect(req)) {
				resp.sendRedirect(viewPage);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
	}
}
