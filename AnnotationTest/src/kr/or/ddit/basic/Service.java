package kr.or.ddit.basic;

/**
 * 애너테이션 사용 예제
 */

public class Service {
	
	@PrintAnnotation
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "%") // == @PrintAnnotation("%")
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "#", count = 30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
}
