package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRace {
	public static int ranking = 1;
	public static List<RunHorse> runHorses = new ArrayList<RunHorse>();
	
	public static void main(String[] args) {
		runHorses.add(new RunHorse("01번말"));
		runHorses.add(new RunHorse("02번말"));
		runHorses.add(new RunHorse("03번말"));
		runHorses.add(new RunHorse("04번말"));
		runHorses.add(new RunHorse("05번말"));
		runHorses.add(new RunHorse("06번말"));
		runHorses.add(new RunHorse("07번말"));
		runHorses.add(new RunHorse("08번말"));
		runHorses.add(new RunHorse("09번말"));
		runHorses.add(new RunHorse("10번말"));
		
		HorsePosition hp = new HorsePosition();
		hp.start();
		
		for (int i = 0; i < runHorses.size(); i++) {
			runHorses.get(i).start();
		}
		
		try {
			hp.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println("========== 경기 종료 ==========");
		System.out.println();
		
		Collections.sort(runHorses);
		
		for (int i = 0; i < runHorses.size(); i++) {
			System.out.println(runHorses.get(i).gethorseRank() + "등 : " + runHorses.get(i).getHorseName());
		}
	}
}

class RunHorse extends Thread implements Comparable<RunHorse> {

	private String horseName;
	private int horseRank;
	private int horsePosi;

	public RunHorse(String horseName) {
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int gethorseRank() {
		return horseRank;
	}

	public void sethorseRank(int horseRank) {
		this.horseRank = horseRank;
	}

	public int getHorsePosi() {
		return horsePosi;
	}

	public void setHorsePosi(int horsePosi) {
		this.horsePosi = horsePosi;
	}

	@Override
	public int compareTo(RunHorse horse) {
		return Integer.compare(horseRank, horse.gethorseRank());
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setHorsePosi(i);
		}
		this.horseRank = HorseRace.ranking;
		HorseRace.ranking++;
	}
}

class HorsePosition extends Thread {
	@Override
	public void run() {
		
		while(true) {
			int cnt = 0;
			System.out.println("====================== START! ======================");
			for(int i = 0; i < HorseRace.runHorses.size(); i++) {
				String course = "--------------------------------------------------";
				RunHorse horse = HorseRace.runHorses.get(i);
				
				if(horse.getHorsePosi() != 49) {
					System.out.print(horse.getHorseName() + " : ");
					System.out.print(course.substring(0, horse.getHorsePosi()) + ">");
					System.out.println(course.substring(horse.getHorsePosi(), 49));
				} else {
					System.out.print(horse.getHorseName() + " : ");
					System.out.print(course.substring(0, horse.getHorsePosi()) + horse.gethorseRank() + "등!");
					System.out.println();
					cnt++;
				}
			}
			
			for(int j = 0; j < 10; j++) {
				System.out.println();
			}
			
			if(cnt == HorseRace.runHorses.size()) {
				return;
			}
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
