package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.StringTokenizer;

/**
 * 간단한 웹서버 예제
 */
public class MyHttpServer {
	
	private final int prot = 80;
	private final String encoding = "UTF-8";
	
	/**
	 * 응답 헤더 생성하기
	 * @param contentLength 응답내용 크기
	 * @param mimeType      마임타입
	 * @return 바이트 배열
	 */
	private byte[] makeResponseHeader(int contentLength, String mimeType) {
		String header = "HTTP/1.1 200 OK\r\n"
				+ "Server: MyHTTPServer 1.0\r\n"
				+ "Content-length: " + contentLength + "\r\n"
				+ "Content-type: " + mimeType + "; charset="
				+ this.encoding + "\r\n\r\n";
		
		return header.getBytes();
	}
	
	/**
	 * 응답 내용 생성하기
	 * @param filePath 응답으로 사용할 파일 경로
	 * @return 바이트배열 데이터
	 * @throws IOException
	 */
	private byte[] makeResponseBody(String filePath) throws IOException {
		FileInputStream fis = null;
		byte[] data = null;
		try {
			File file = new File(filePath);
			data = new byte[(int)file.length()];
			
			fis = new FileInputStream(file);
			fis.read(data);
		} finally {
			if(fis != null) {
				fis.close();
			}
		}
		return data;
	}
	
	/**
	 * 에러 페이지 생성
	 * @param out
	 * @param statusCode
	 * @param errMsg
	 */
	private void makeErrorPage(OutputStream out, int statusCode, String errMsg) {
		String statusLine = "HTTP/1.1" + " " + statusCode
							+ " " + errMsg;
		try {
			out.write(statusLine.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * HTTP 서버 시작
	 */
	public void startServer() {
		
		try(ServerSocket server = new ServerSocket(this.prot)){
			
			while(true) {
				try {
					Socket socket = server.accept();
					
					// Http 요청처리를 위한 쓰레드 객체 생성
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start();
					
				} catch (IOException ex) {
					System.err.println("커넥션 오류!!!");
					ex.printStackTrace();
				} catch (RuntimeException ex) {
					System.err.println("알수 없는 오류!!");
					ex.printStackTrace();
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * HTTP 요청 처리를 위한 Runnable 객체
	 */
	private class HttpHandler implements Runnable {
		
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				out = new BufferedOutputStream(
						socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream()));
				
				// 요청헤더 정보 파싱하기
				StringBuilder request = new StringBuilder();
				while(true) {
					String str = br.readLine();
					
					if(str.equals("")) break; // emptyLine 체크
					
					request.append(str + "\n");
				}
				System.out.println("요청헤더:\n" + request.toString());
				
				String reqPath = "";
				
				// 요청페이지 정보 가져오기
				StringTokenizer st = 
						new StringTokenizer(request.toString());
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					if(token.startsWith("/")) {
						reqPath = token;
					}
				}
				
				// 상대경로(프로젝트 폴더 기준) 설정
				 String fileName = "./WebContent" + reqPath;
				 
				 // 해당 파일이름을 이용하여 Content-type 정보 추출하기
				 String contentType = 
						 URLConnection.getFileNameMap().getContentTypeFor(fileName);
				 System.out.println("contentType => " + contentType);
				 
				 File file = new File(fileName);
				 if(!file.exists()) {
					 makeErrorPage(out, 404, "Not Found");
					 return;
				 }
				 
				 byte[] body = makeResponseBody(fileName);
				 byte[] header = 
						 makeResponseHeader(body.length, contentType);
				 
				 // 요청헤더가 HTTP/1.0이나 그 이후의 버전을 지원할 경우 MIME헤더를 전송한다.
				 if(request.toString().indexOf("HTTP/") != -1) {
					 out.write(header); // 응답헤더 보내기
				 } 
				 
				 System.out.println("응답헤더:\n" + new String(header));
				 
				 out.write(body); // 응답내용 보내기
				 out.flush();
				 
			} catch (IOException ex) {
				System.err.println("입출력 오류!!");
				ex.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MyHttpServer().startServer();
	}
}