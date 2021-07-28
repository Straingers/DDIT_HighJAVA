package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		
		// PrintAnnotation의 static변수값 출력하기
		System.out.println(PrintAnnotation.id);
		
		// Reflection API를 이용하여 메서드 정보 추출 및 실행
		Method[] methodArr = Service.class.getDeclaredMethods();
		
		for(Method m : methodArr) {
			System.out.println(m.getName()); // 메서드명 출력
			
			// 애너테이션 객체 가져오기
			PrintAnnotation printAnn = m.getDeclaredAnnotation(PrintAnnotation.class);
			
			for (int i = 0; i < printAnn.count(); i++) { // count값만큼 ...
				System.out.print(printAnn.value()); // value값 출력
			}
			System.out.println(""); // 줄바꿈 처리
			
			// 메서드 실행 1
			// m.invoke(new Service()); 
			
			// 메서드 실행 2
			Class<Service> clazz = Service.class;
			
			Service service = (Service) clazz.newInstance();
			
			m.invoke(service);
		}
	}
}
