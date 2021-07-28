package kr.or.ddit.basic;

import java.util.Arrays;

/**
 * 와일드카드 타입 예제
 */
public class T07_WildcardTest {
	
	/**
	 * 모든 과정 수강정보 조회
	 * course 모든 과정 
	 */
	public static void listCourseInfo(Course<?> course) {
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 학생과정 수강정보 조회
	 */
	public static void listStudentCourseInfo(Course<? extends Student> course) {
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	/**
	 * 직장인 및 일반인 과정  수강정보 조회
	 */
	public static void listWorkerCourseInfo(Course<? super Worker> course) {
		System.out.println(course.getName() + " 수강생 : " + Arrays.toString(course.getStudents()));
	}
	
	public static void main(String[] args) {
		Course<Person> personCourse = new Course<>("일반인과정", 5);
		personCourse.add(new Person("일반인1"));
		personCourse.add(new Worker("직장인1"));
		personCourse.add(new Student("학생1"));
		personCourse.add(new HighStudent("고등학생1"));
		
		Course<Worker> workerCource = new Course<>("직장인과정", 5);
		workerCource.add(new Worker("직장인1"));
		
		Course<Student> studentCource = new Course<>("학생과정", 5);
		studentCource.add(new Student("학생1"));
		studentCource.add(new HighStudent("고등학생1"));
		
		Course<HighStudent> highStudentCource = new Course<>("고등학생과정", 5);
		highStudentCource.add(new HighStudent("고등학생1"));
		
		listCourseInfo(personCourse);
		listCourseInfo(workerCource);
		listCourseInfo(studentCource);
		listCourseInfo(highStudentCource);
		System.out.println("--------------------------------------------");
		
//		listStudentCourseInfo(personCourse);
//		listStudentCourseInfo(workerCource);
		listStudentCourseInfo(studentCource);
		listStudentCourseInfo(highStudentCource);
		System.out.println("--------------------------------------------");
		
		listWorkerCourseInfo(personCourse);
		listWorkerCourseInfo(workerCource);
//		listWorkerCourseInfo(studentCource);
//		listWorkerCourseInfo(highStudentCource);
	} 
}

/**
 * 일반인
 */
class Person {
	String name; // 이름

	public Person(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "이름 : " + name;
	}
	
}

/**
 * 직장인
 */
class Worker extends Person {
	public Worker(String name) {
		super(name);
	}
}

/**
 * 학생
 */
class Student extends Person {
	public Student(String name) {
		super(name);
	}
}

/**
 * 고등학생
 */
class HighStudent extends Student {
	public HighStudent(String name) {
		super(name);
	}
}

/**
 * 수강과정
 */
class Course<T> {
	private String name; // 과정명
	private T[] students; // 수강생(제너릭 배열)
	
	public Course(String name, int capacity) {
		this.name = name;

		// 타입파라미터로 배열을 생성시 오브젝트 배열을 생성후, 타입 파라미터 배열로 캐스팅 처리해야함.
//		students = new T[capacity]; // 제너릭 배열 생성 실패 
									// => new 연산자는 생성할 객체의 타입이 명확히 정의되어야 객체생성 가능
		students = (T[])(new Object[capacity]);
	}
	
	// 과정명 조회
	public String getName() {
		return name;
	}
	
	// 수강생 조회
	public T[] getStudents() {
		return students;
	}
	
	// 수강생 등록
	public void add(T t) {
		for (int i = 0; i < students.length; i++) {
			if (students[i] == null) { // 아직 등록되지 않은(빈) 자리인지 확인
				students[i] = t;
				break;
			}
		}
	}
}