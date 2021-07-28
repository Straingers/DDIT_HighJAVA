package homework;

public class PlanetArea {
	/*문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)*/
	
	public enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 
		목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		
		private int radious;
		
		public int getRadious() {
			return radious;
		}
		
		Planet(int radious) {
			this.radious = radious;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("행성의 반지름(Km)");
		System.out.println("================");
		System.out.println("행성\t반지름");
		System.out.println("----------------");
		for(Planet p : Planet.values()) {
			System.out.println(p + "\t" + p.getRadious() + "Km");
		}
		System.out.println("================");
		
	}
	
}
