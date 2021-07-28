package kr.or.ddit.basic;

class Util2 {
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

/**
 * 제한된 타입 파라미터(Bounded Parameter) 예제 
 */
public class T05_GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20); // AutoBoxing
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);
		System.out.println(result2);
		
		//Util2.compare("c", "java"); // 불가! 왜 why? 위에서 제너릭으로 Number클래스만 쓸 수 있게 제한 했기 때문
	}
}
