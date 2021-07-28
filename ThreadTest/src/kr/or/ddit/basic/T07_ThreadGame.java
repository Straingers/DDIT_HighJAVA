package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
  컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
  
  컴퓨터의 가위 바위 보는 난수를 이용하여 구하고,
  사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
  
  입력시간은 5초로 제한하고 카운트 다운을 진행한다.
  5초안에 입력이 없으면 게임을 진것으로 처리한다.
  
  5초안에 입력이 완료되면 승패를 출력한다.
  
  결과예시)
  === 결과 ===
  컴퓨터 : 가위
  당   신 : 바위
  결   과 : 당신이 이겼습니다.
 */
public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		Thread th1 = new rspInput();
		Thread th2 = new CountDown2();
		
		th1.start();
		th2.start();
	}
}

class rspInput extends Thread {
	@Override
	public void run() {
		String user = "";
		while(true) {
			user = JOptionPane.showInputDialog("가위, 바위, 보");
			if(user.equals("가위") || user.equals("바위") || user.equals("보")) {
				break;
			}
		}
		String[] rsp = {"가위", "바위", "보"};
		
		T07_ThreadGame.inputCheck = true;
		
		int comIdx = (int)(Math.random() * 3);
		int userIdx = 0;
		String com = "";

		String result = "";
		
		for(int i = 0; i < rsp.length; i++) {
			if(user.equals(rsp[i])) {
				userIdx = i;
			}
		}
		if(userIdx < comIdx) {
			com = rsp[comIdx];
			result = "당신이 졌습니다.";
			if(userIdx == 0 && comIdx == 2) {
				result = "당신이 이겼습니다";
			}
		} else if(userIdx > comIdx) {
			com = rsp[comIdx];
			result = "당신이 이겼습니다.";
			if(userIdx == 2 && comIdx == 0) {
				result = "당신이 졌습니다";
			}
		} else if(userIdx == comIdx) {
			com = rsp[comIdx];
			result = "비겼습니다.";
		}
		System.out.println("====== 결과 ======");
		System.out.println("컴퓨터 : " + com + "\n당   신 : " + user + "\n결   과 : " + result);
		System.exit(0);
	}
}

class CountDown2 extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if(T06_ThreadTest.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("입력시간을 초과했습니다.\n당신은 패배하였습니다.");
		System.exit(0);
	}
}