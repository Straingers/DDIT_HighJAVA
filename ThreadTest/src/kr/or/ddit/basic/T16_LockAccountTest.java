package kr.or.ddit.basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 은행의 입출금을 쓰레드로 처리하는 예제
 * (Lock 객체를 이용한 동기화 처리)
 */
public class T16_LockAccountTest {
/*
   Lock 기능을 제공하는 클래스
   
   ReentrantLock : Read 및 Write 구분없이 사용하기 위한 락 클래스로 동기화 처리를 위해 사용됨.
   				=> Synchronized를 이용한 동기화처리보다 부가적인 기능이 제공됨.
   				   ex)Fairness설정 등
   ReentrantReadWriteLock : Read 및 Write락을 구분하여 사용가능함. (Fair mode)
 */
	public static void main(String[] args) {
		
		ReentrantLock lock = new ReentrantLock(); // 락객체 생성
		
		LockAccount lAcc = new LockAccount(lock); // 공유객체 생성
		lAcc.deposit(10000); // 입금
		
		BankThread2 bth1 = new BankThread2(lAcc);
		BankThread2 bth2 = new BankThread2(lAcc);
		
		bth1.start();
		bth2.start();
		
	}
}

// 입출금을 담당하는 클래스
class LockAccount {
	
	private int balance; // 잔액
	
	// Lock객체 저장할 변수 => 가능하면 private final로 만든다.
	private final Lock lock;
	
	public LockAccount(Lock lock) {
		this.lock = lock;
	}
	
	public int getBalance() {
		return balance;
	}
	
	// 입금 처리 메서드
	public void deposit(int money) {
		// Lock객체의 lock()메서드가 동기화 시작이고, unlock()메서드가 동기화의 끝을 나타낸다.
		// lock()메서드로 동기화를 설정한 곳에서는 반드시 unlock()메서드로 헤제해 주여아 한다.
		lock.lock(); // 잠금 시작(락을 획득하기 전까지 BLOCKED 됨.)
		balance += money;
		lock.unlock(); // 해제
	}
	
	// 출금 처리 메서드 (출금 성공 : true, 출금 실패 : false)
	public boolean withDraw(int money) {
		
		lock.lock(); // 락 획득
		boolean chk = false;
		// try ~ catch 블럭을 사용할 경우에는 unlock()메서드 호출은 finally 블럭에서 하도록 한다.
		try {
			if(balance >= money) {
				for (int i = 1; i < 1000000000; i++) {} // 시간 때우기
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			}
		} catch (Exception ex) {
			chk = false;
		} finally {
			lock.unlock(); // 락 반납(해제)
		}
		return chk;
	}
}

// 은행 업무를 처리하는 쓰레드
class BankThread2 extends Thread {
	
	private LockAccount lAcc;
	
	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withDraw(6000);
		System.out.println("쓰레드 안에서 result = " + result 
						+ ", balance = " + lAcc.getBalance());
	}
}