package kr.or.ddit.rmi.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.rmi.inf.RemoteInterface;
import kr.or.ddit.rmi.vo.FileInfoVO;
import kr.or.ddit.rmi.vo.TestVO;

/**
 * RMI용 서비스를 제공하는 객체는 RMI용 인터페이스를 구현하고 
 * UnicastRemoteObject객체를 상속해서 작성한다.
 */
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface {

	protected RemoteServer() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) {
		try {
			// 구현한 RMI용 객체를 클라이언트에서 사용할 수 있도록 RMI서버에 등록한다.
			
			// 1. RMI용 인터페이스를 구현한 객체 생성
			RemoteInterface inf = new RemoteServer();
			
			// 2. 구현한 객체를 클라이언트에서 찾을 수 있도록 Registry객체를 생성해서 등록한다.
			
			// 포트 번호를 지정하여 Registry객체 생성(기본포트:1099)
			Registry reg = LocateRegistry.createRegistry(8888);
			
			// Registry 서버에 제공하고자 하는 객체 등록
			// 형식) Registry객체변수.rebind("객체의Alias", 객체변수);
			reg.rebind("server", inf);
			
			System.out.println("서버가 준비되었습니다.");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int doRemotePrint(String str) throws RemoteException {
		int length = str.length();
		System.out.println("클라이언트에서 보내온 메시지 : " + str);
		System.out.println("출력 끝.");
		
		return length;
	}

	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("클라이언트에서 보낸 List값들...");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1) + "번째 : " + list.get(i));
		}
		System.out.println("List 출력 끝...");
	}

	@Override
	public void doPrintVO(TestVO vo) throws RemoteException {
		System.out.println("클라이언트에서 보내온 TestVO객체 값 출력");
		System.out.println("testId : " + vo.getTestId());
		System.out.println("testNum : " + vo.getTestNum());
		System.out.println("TestVO 객체 출력 끝...");
	}

	@Override
	public void setFiles(FileInfoVO[] fInfo) throws RemoteException {
		FileOutputStream fos = null;
		
		String dir = "d:/C_Lib/"; // 파일이 저장될 폴더 지정
		
		System.out.println("파일 저장 시작...");
		
		for(FileInfoVO fvo : fInfo) {
			try {
				fos = new FileOutputStream(dir + fvo.getFileName());
				// 클라이언트에서 전달한 파일 데이터(byte[])를 서버측에 저장한다.
				fos.write(fvo.getFileData());
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("파일 저장 완료...");
	}
}
