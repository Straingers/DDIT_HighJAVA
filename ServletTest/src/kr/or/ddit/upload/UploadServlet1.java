package kr.or.ddit.upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * oreilly의 MultipartRequest를 이용한 파일 업로드 예제
 * (생성자 호출과 동시에 파일이 생성되기 때문에 선택적인 파일 생성처리 불가)
 */
public class UploadServlet1 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String encType = "UTF-8";
		int maxFileSize = 5 * 1024 * 1024;
		
		// MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩캐릭터셋, 동일한 파일명 보호여부]
		// DefaultFileRenamePolicy => name.zip, name1.zip, name2.zip, ...
		MultipartRequest mr = 
				new MultipartRequest(req, "d:/D_Other/", maxFileSize
								, encType, new DefaultFileRenamePolicy());
		
		File file = mr.getFile("fname");
		System.out.println(file); // 첨부된 파일의 전체경로 출력
		
		System.out.println(mr.getParameter("title")); // 파라미터 값 가져오기
		
		out.print("업로드 완료됨.");
	}
}
