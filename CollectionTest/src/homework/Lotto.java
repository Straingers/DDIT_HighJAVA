package homework;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	 /*로또를 구매하는 프로그램 작성하기
	 
	 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	 입력한 금액에 맞게 로또번호를 출력한다.
	 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
	      출력한다.)

		==========================
	         Lotto 프로그램
		--------------------------
		 1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 1  <-- 입력
				
		 Lotto 구입 시작
			 
		(1000원에 로또번호 하나입니다.)
		금액 입력 : 2500  <-- 입력
				
		행운의 로또번호는 아래와 같습니다.
		로또번호1 : 2,3,4,5,6,7
		로또번호2 : 20,21,22,23,24,25
				
		받은 금액은 2500원이고 거스름돈은 500원입니다.
				
	   	 ==========================
	         Lotto 프로그램
		--------------------------
		  1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 2  <-- 입력
			
		감사합니다*/
	
	
	public static void main(String[] args) {
		
		Draw draw = new Draw();
		draw.start();
	}

}

class Draw {
	
	Scanner sc = new Scanner(System.in);
	
	// 시작
	public int start(){
		while(true) {
			System.out.println("=====================");
			System.out.println("Lotto 프로그램");
			System.out.println("---------------------");
			System.out.println("[1]Lotto 구입");
			System.out.println("[2]프로그램 종료");
			System.out.println("---------------------");
			System.out.print("메뉴선택 > ");
			int input = 0;
			try {
				input = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("잘못입력하셨습니다. 다시 입력하세요!");
			}
			switch (input) {
				case 1: getNum(); 
				case 2: 
					System.out.println();
					System.out.println("감사합니다.");
					System.exit(0);
			}
			
		}
		
	}

	// 로또 구입
	private void getNum() {
		System.out.println("======================");
		System.out.println("Lotto 구입 시작");
		System.out.println("(1000원에 로또번호 하나입니다.)");
		System.out.println("----------------------");
		System.out.print("금액 입력 > ");
		int money = 0;
		try {
			money = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
			System.out.println("잘못입력하셨습니다. 다시 입력하세요!");
		}
		if (money < 1000) {
			System.out.println("잔돈이 부족합니다.");
			getNum();
		} else {
			System.out.println();
			System.out.println("행운의 로또 번호는 아래와 같습니다.");
			System.out.println("---------------------------------------");
			if (money % 1000 == 0) {
				for(int i = 0; i < money / 1000; i++) {
					Set<Integer> set = new HashSet<>();
					
					while(set.size() < 6) {
						set.add((int)(Math.random() * 45 + 1));
					}
					System.out.println("로또번호" + (i + 1) + " : " + set);
				}
				System.out.println("---------------------------------------");
				System.out.println("받은 금액은 " + money + "원 이고, 거스름돈은 " + (money - (money / 1000) * 1000) + "원 입니다.");
				start();
			} else {
				for(int i = 0; i < money / 1000; i++) {
					Set<Integer> set = new HashSet<>();
					
					while(set.size() < 6) {
						set.add((int)(Math.random() * 45 + 1));
					}
					System.out.println("로또번호" + (i + 1) + " : " + set);
				}				
				System.out.println("---------------------------------------");
				System.out.println("받은 금액은 " + money + "원 이고, 거스름돈은 " + (money - (money / 1000) * 1000) + "원 입니다.");
				start();
			}
		}
	}
}
