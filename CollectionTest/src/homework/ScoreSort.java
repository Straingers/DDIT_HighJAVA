package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreSort {
	/*학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	  Student클래스를 만든다.
	  생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
	  
	  이 Student객체들은 List에 저장하여 관리한다.
	  List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
	  총점의 역순으로 정렬하는 부분을 프로그램 하시오.
	  (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
	  (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
	   총점 정렬기준은 외부클래스에서 제공하도록 한다.)

	  학번 - String*/
	
	public static void main(String[] args) {
		int kor = 0;
		int eng = 0;
		int math = 0;
		
		// 랜덤으로 국어, 영어, 수학 점수 입력
		List<Student> stuList = new ArrayList<Student>();
		ScoreRnd score = new ScoreRnd();
		stuList.add(new Student("a009", "이광렬", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a005", "최영준", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a011", "김이현", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a019", "김현태", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a007", "변형균", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a018", "전재수", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a010", "김민지", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a020", "서주형", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a003", "정영인", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a017", "김현슬", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a006", "전윤주", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a015", "공슬기", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a014", "최윤성", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a002", "강건우", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a004", "이휘로", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a013", "박예진", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a016", "박상영", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a008", "유은지", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a012", "김두환", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
		stuList.add(new Student("a001", "정유진", score.getScore(kor), score.getScore(eng), score.getScore(math), 0));
				
		// 등수 매기기
		for (int i = 0; i < stuList.size(); i++) {
			int cnt = 2;
			for (int j = 0; j < stuList.size(); j++) {
				if (stuList.get(i).getScore() < stuList.get(j).getScore()) {
					stuList.get(i).setRank(cnt++);
				}
			}
		}
		
		//섞기
		Collections.shuffle(stuList);
		
		System.out.println("정렬 전");
		System.out.println();
		System.out.println("학번\t이름\t국어\t수학\t영어\t총점\t등수");
		System.out.println("-----------------------------------------------------");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("=====================================================");
		System.out.println();
		
		Collections.sort(stuList);
		
		System.out.println("학번의 오름차순으로 정렬한 후");
		System.out.println();
		System.out.println("학번\t이름\t국어\t수학\t영어\t총점\t등수");
		System.out.println("-----------------------------------------------------");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("=====================================================");
		System.out.println();
		
		Collections.sort(stuList, new ScoreDesc());
		System.out.println("총점의 내림차순으로 정렬한 후 (먄약, 점수가 같으면 학번의 내림차순)");
		System.out.println();
		System.out.println("학번\t이름\t국어\t수학\t영어\t총점\t등수");
		System.out.println("-----------------------------------------------------");
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		
	}
}

// 총점의 내림차순 정렬
class ScoreDesc implements Comparator<Student>{

	@Override
	public int compare(Student stu1, Student stu2) {
		if(stu1.getScore() == stu2.getScore()) {
			return stu1.getStuNum().compareTo(stu2.getStuNum()) * -1;
		} else {
			return new Integer(stu1.getScore()).compareTo(stu2.getScore()) * -1;
		}
	}
}

// 랜덤 스코어 입력 해주는 클래스
class ScoreRnd {
	public int getScore(Object a) {
		return (int)(Math.random() * 100 + 1);
	}
}

// Student 클래스
class Student implements Comparable<Student> {
	private String stuNum;
	private String stuName;
	private int kor;
	private int eng;
	private int math;
	private int score;
	private int rank = 1;

	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public Student() {
		this.score = this.kor + this.eng + this.math;
	}

	public Student(String stuNum, String stuName, int kor, int eng, int math, int score) {
		super();
		this.stuNum = stuNum;
		this.stuName = stuName;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.score = this.kor + this.eng + this.math;
	}
	
	@Override
	public String toString() {
		return stuNum + "\t" + stuName + "\t" + kor + "\t" + eng + "\t" + math
				+ "\t" + score + "\t" + rank;
	}
	
	@Override
	public int compareTo(Student stu) {
		return (Integer)(getStuNum()).compareTo(stu.getStuNum());
	}
}