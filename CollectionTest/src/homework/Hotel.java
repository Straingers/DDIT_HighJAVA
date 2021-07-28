package homework;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
/*	문제)

	호텔 운영을 관리하는 프로그램 작성.(Map이용)
	 - 키값은 방번호 
	 
	실행 예시)

	**************************
	호텔 문을 열었습니다.
	**************************

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 1 <-- 입력

	어느방에 체크인 하시겠습니까?
	방번호 입력 => 101 <-- 입력

	누구를 체크인 하시겠습니까?
	이름 입력 => 홍길동 <-- 입력
	체크인 되었습니다.

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 1 <-- 입력

	어느방에 체크인 하시겠습니까?
	방번호 입력 => 102 <-- 입력

	누구를 체크인 하시겠습니까?
	이름 입력 => 성춘향 <-- 입력
	체크인 되었습니다

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
		*******************************************
	메뉴선택 => 3 <-- 입력

	방번호 : 102, 투숙객 : 성춘향
	방번호 : 101, 투숙객 : 홍길동

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 2 <-- 입력

	어느방을 체크아웃 하시겠습니까?
	방번호 입력 => 101 <-- 입력
	체크아웃 되었습니다.

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 1 <-- 입력

	어느방에 체크인 하시겠습니까?
	방번호 입력 => 102 <-- 입력

	누구를 체크인 하시겠습니까?
	이름 입력 => 허준 <-- 입력
	102방에는 이미 사람이 있습니다.

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 2 <-- 입력

	어느방을 체크아웃 하시겠습니까?
	방번호 입력 => 101 <-- 입력
	101방에는 체크인한 사람이 없습니다.

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 3 <-- 입력

	방번호 : 102, 투숙객 : 성춘향

	*******************************************
	어떤 업무를 하시겠습니까?
	1.체크인  2.체크아웃 3.객실상태 4.업무종료
	*******************************************
	메뉴선택 => 4 <-- 입력

	**************************
	호텔 문을 닫았습니다.
	***************************/
	
	private Scanner sc = new Scanner(System.in);
	
	private Map<String, String> hotel = new HashMap<String, String>();
	private Set<String> key = new HashSet<>();
	
	// 시작 메서드
	public int start() {
		System.out.println("**************************");
		System.out.println("호텔문을 열었습니다.");
		System.out.println("**************************");
		System.out.println();
		while(true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("[1]체크인 [2]체크아웃 [3]객실상태 [4]업무종료");
			System.out.println("*******************************************");
			System.out.print("입력 > ");
			int input = 0;
			try {
				input = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요~");
			}
			switch (input) {
				case 1: checkIn(); break; // 체크인
				case 2: checkOut(); break; // 체크 아웃
				case 3: roomCheck(); break; // 방확인
				case 4:
					System.out.println();
					System.out.println("호텔 문을 닫았습니다.");
					System.exit(0);
					break;
			}
		}
		
	}
	
	// 체크인 메서드
	private void checkIn() {
		while(true) {
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.println("------------------------");
			System.out.println("101, 102, 103, 104");
			System.out.println("201, 202, 203, 204");
			System.out.println("301, 302, 303, 304");
			System.out.println("------------------------");
			System.out.print("호실 입력 > ");
			String room = sc.nextLine();
			System.out.println();
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.println("------------------------");
			System.out.print("이름 입력 > ");
			String name = sc.nextLine();
			
			boolean flag = key.add(room); // Set클래스의 중복을 허용하지 않는 특성을 이용
			
			if(flag == false) {
				System.out.println(room + "호에는 이미 사람이 있습니다.");
				System.out.println();
			} else {
				hotel.put(room, name);
				System.out.println("체크인 되었습니다.");
				System.out.println();
				System.out.println("*******************************************");
				return;
			}
		}
	}
	
	// 체크아웃 메서드
	private void checkOut() {
		while(true) {
			System.out.println("어느방을 체크아웃 하시겠습니까?");
			System.out.println("------------------------");
			System.out.print("호실 입력 > ");
			String room = sc.nextLine();
			
			boolean flag = key.add(room); // Set클래스의 중복을 허용하지 않는 특성을 이용
			
			if(flag == false) {
				hotel.remove(room);
				key.remove(room);
				System.out.println("체크아웃 되었습니다.");
				System.out.println();
				return;
			} else {
				System.out.println(room + "호실에는 체크인한 사람이 없습니다.");
				System.out.println();
				key.remove(room);
				return;
			}
		}
	}

	// 방 체크
	private void roomCheck() {
		
		Set<String> keySet = hotel.keySet(); // 키값들을 집합시킴
		
		System.out.println("======================");
		System.out.println(" 번호\t이름\t방번호");
		System.out.println("======================");
		
		if(keySet.size() == 0) { 
			System.out.println("예약된 방이 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while(it.hasNext()) {
				cnt++;
				String name = it.next();
				System.out.println(" " + cnt + "\t" + hotel.get(name) + "\t" + name + "호");
			}

		}
		System.out.println("======================");
	}

	
	//메인 메서드
	public static void main(String[] args) {
		new Hotel().start();
	}

	
}
