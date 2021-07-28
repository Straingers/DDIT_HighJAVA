package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.(Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
예)
1번말 --->------------------------------------
2번말 ----->----------------------------------
...

경기가 끝나면 등수를 기준으로 정렬하여 출력한다.
 */
public class HorseRacing {
	
	static int rank;
	
	public static void main(String[] args) {
		List<Horse> horses = new ArrayList<Horse>(); 
		horses.add(new Horse("01번말", 0));
		horses.add(new Horse("02번말", 0));
		horses.add(new Horse("03번말", 0));
		horses.add(new Horse("04번말", 0));
		horses.add(new Horse("05번말", 0));
		horses.add(new Horse("06번말", 0));
		horses.add(new Horse("07번말", 0));
		horses.add(new Horse("08번말", 0));
		horses.add(new Horse("09번말", 0));
		horses.add(new Horse("10번말", 0));
		
		for (int i = 0; i < horses.size(); i++) {
			horses.get(i).start();
		}
		for(Horse h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		System.out.println("\t 경기 종료!");
		System.out.println();
		System.out.println("============== 경기결과 ==============");
		Collections.sort(horses);
		for(int i = 0; i < horses.size(); i++) {
			Horse h = horses.get(i);
			System.out.print((i + 1) + "등 : " + h.getHorseNo());
			System.out.println();
		}
	}
}

class Horse extends Thread implements Comparable<Horse>{

	private String horseNo;
	private int rank;
	
	public String getHorseNo() {
		return horseNo;
	}
	public void setHorseNo(String horseNo) {
		this.horseNo = horseNo;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public Horse(String horseNo, int rank) {
		super();
		this.horseNo = horseNo;
		this.rank = rank;
	}

	@Override
	public void run() {
		for(int i = 1; i <= 20; i++){
			System.out.print(horseNo);
			for(int j = 0; j < i - 1; j++){
				System.out.print(">");
			}
			System.out.print("▶");
			for(int k = 0; k < 20 - i; k++){
				System.out.print("-");
			}
			System.out.println();			
			System.out.println();			
			try {
				Thread.sleep((int)(Math.random() * 501 + 200));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("\t" + horseNo + " 도착!");
		HorseRacing.rank++;
		rank = HorseRacing.rank;
	}
	
	@Override
	public int compareTo(Horse h) {
		if (rank > h.getRank()) {
			return 1;
		} else {
			return -1;
		}
	}
}
