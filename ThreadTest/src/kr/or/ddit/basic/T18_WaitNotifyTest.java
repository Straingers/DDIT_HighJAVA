package kr.or.ddit.basic;

public class T18_WaitNotifyTest {
/*
   wait()메서드 => 동기화 영역에서 락을 풀고 wait-set영역(공유객체별 존재)으로 이동시킨다.
   notify() 또는 notifyAll()메서드 => wait-set영역에 있는 쓰레드를 깨워서 실행될 수 있도록 한다.
 								  (notify()는 하나, notifyAll()은 모두 깨운다.)
   => wait()과 notify(), notifyAll()메서드는 동기화 영역에서만 실행할 수 있고,
      Object클래스에서 제공하는 메서드이다.
 */
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		Thread th1 = new ThreadA(workObj);
		Thread th2 = new ThreadB(workObj);
		
		th1.start();
		th2.start();
		
	}
}

// 공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.err.println("mothodA()메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void methodB() {
		System.err.println("mothodB()메서드 작업 중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// workObj의 methodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

// workObj의 methodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}
