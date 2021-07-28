package kr.or.ddit.reflection;

import java.lang.reflect.Modifier;

/**
 * Class의 메타데이터 가져오기 예제
 */
public class T02_ClassMetadataTest {
	public static void main(String[] args) {
		// 클래스 오브젝트 생성하기
		Class<?> clazz = SampleVO.class;
		
		System.out.println("심플클래스 명 : " + clazz.getSimpleName());
		System.out.println("클래스 명 : " + clazz.getName());
		System.out.println("상위 클래스 명 : " + clazz.getSuperclass());
		
		// 해당 클래스에서 구현하고 있는 인터페이스 목록 가져오기
		Class<?>[] interfaceList = clazz.getInterfaces();
		
		System.out.println("인터페이스 목록");
		for(Class<?> inf : interfaceList) {
			System.out.print(inf.getName() + " | ");
		}
		System.out.println();
		
		// 클래스의 접근제어자 가져오기
		int modFlag = clazz.getModifiers();
		System.out.println("접근 제어자 : " + Modifier.toString(modFlag));
		
		// 패키지 정보 가져오기
		Package pkg = clazz.getPackage();
		System.out.println("패키지 정보 : " + pkg.getName());
	}
}
