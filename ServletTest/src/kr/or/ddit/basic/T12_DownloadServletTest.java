package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T12_DownloadServletTest extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String filename = "lion.jpg";
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition"
						, "attachment; filename=\"" 
							+ filename + "\"");
	/**
	    Content-Disposition 헤더에 대하여...
	    
	   1. response header에서 사용되는 경우... ex) 파일다운로드
	   
	    Content-Disposition : inline (default)
	    // 파일 다운로드
	    Content-Disposition : attachment
	    // filename.jpg 이름으로 파일 다운로드
	    Content-Disposition : attachment; filename="filename.jpg"
	 	
	   2. multipart body를 위한 헤더 정보로 사용되는 경우... ex) 파일 업로드
	    Content-Disposition : form-data
	    Content-Disposition : form-data; name="fieldName"
	    Content-Disposition : form-data; name="fieldName"; filename="filename.jpg"
	 */
		
		ServletOutputStream out = resp.getOutputStream();
		
		FileInputStream fis = new FileInputStream("d:/D_Other/lion.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int bytes = 0; // 읽은 바이트 수
		while((bytes = bis.read()) != -1) {
			bos.write(bytes);
		}
		bis.close();
		bos.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
