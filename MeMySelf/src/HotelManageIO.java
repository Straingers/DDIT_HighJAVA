import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//설계 순서 : VO생성(직렬화 Imple) 
//			-> HotelManageIO 클래스 생성 
//				-> 필요한 Util Import후 생성자로 전역변수 설정
//					-> 파일 불러오기 
//						-> CRUD메서드 생성 
//							-> 파일 저장하기 
//								-> 종료


// 사용할 변수를 담는 VO 생성
class Hotel implements Serializable { //직렬화
	private String name;
	private int roomNum;
	
	public Hotel(String name, int roomNum) {
		super();
		this.name = name;
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", roomNum=" + roomNum + "]";
	}
	
}


public class HotelManageIO {
	private Scanner scan;
	private Map<Integer, Hotel> hotelManage;
	
	// 생성자를 통해 파라미터를 받을수 있게 준비해준다(그러나 이 프로그램에서는 파라미터를 받을 필요가 없으므로 없어도 무방하다)
	public HotelManageIO() {
		scan = new Scanner(System.in);
		hotelManage = new HashMap<Integer, Hotel>();
	}
	
	public static void main(String[] args) {
		new HotelManageIO().hotelStart();
	}
	
	// 시작화면(View) + FileInputStream + BufferedInputStream + ObjectInputStream(파일 불러오기)
	public void hotelOpen() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");

		try {
			ObjectInputStream ois = 
					new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream("d:/D_Other/hotelObj.bin")));
			
			Object obj = null;
			
			System.out.println();
			System.out.println("현재 저장된 예약정보 :  ");
			while((obj = ois.readObject()) != null) {
				Hotel hotel = (Hotel) obj;
				//hotelObj.bin에 입력할 내용
				System.out.println();
				System.out.println("**************************");
				System.out.println("방번호 : " + hotel.getRoomNum());
				System.out.println("투숙객: " + hotel.getName());
				System.out.println("**************************");
				hotelManage.put(hotel.getRoomNum(), hotel);
			}
			ois.close();
		} catch(EOFException ex) {
			System.out.println("Data Loaded..");
		} catch(IOException|ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	

	
	// 메뉴 화면(View)
	public void displayMenu() {
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.println("메뉴선택 => ");
	}
	
	// 호텔 시작메뉴(Controller)
	public void hotelStart() {
		
		hotelOpen();
		
		while(true) {
			
			displayMenu();
			
			int menuNum = scan.nextInt();
			
			switch(menuNum) {
			case 1 : checkIn();
				break;
			case 2 : checkOut();
				break;
			case 3 : roomStatus();
				break;
			case 4 : close();
				break;
			default :
				System.out.println("다시 입력해주세요.");
			}
		}
	}
	
	// 체크인 메뉴
	private void checkIn() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.println("방번호 입력 => ");
		int roomNum = scan.nextInt();
		
		if(hotelManage.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}
		
		System.out.println("투숙객 성명을 입력해주세요.");
		System.out.println("이름 입력 => ");
		String name = scan.next();
		
		hotelManage.put(roomNum, new Hotel(name, roomNum));
		System.out.println("체크인 완료");
	}
	
	// 체크아웃 메뉴
	private void checkOut() {
		System.out.println("체크아웃하려는 호실을 입력해주세요.");
		System.out.println("방 번호 입력 => ");
		
		int roomNum = scan.nextInt();
		
		if(hotelManage.remove(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println(roomNum + "호실의 체크아웃이 완료되었습니다.");
		}
		
		
	}
	
	private void roomStatus() {
		
		Set<Integer> keySet = hotelManage.keySet();
		
		System.out.println("*******************************************");
		
		if(keySet.size() == 0) {
			System.out.println("예약된 객실이 없습니다.");
		} else {
			Iterator<Integer> it = keySet.iterator();
			// hasNext() : 남아있는 값이 있으면 true, 없으면 false를 반환
			// next() : 요소를 읽어와 반환	
			while(it.hasNext()) {
				int roomNum = it.next();
				Hotel hv = hotelManage.get(roomNum);
				System.out.println("방번호 : " + hv.getRoomNum()
							   + "\t투숙객 : " + hv.getName());
			}
		}
	}
	
	// 종료화면(View) + FileOutputStream + BufferedOutputStream + ObjectOutputStream(파일 저장하기)
	public void close() {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
		System.out.println();
		
		try {
			// 출력용 스트림 객체 생성
			ObjectOutputStream oos = 
					new ObjectOutputStream(
							new BufferedOutputStream(
									new FileOutputStream("d:/D_Other/hotelObj.bin")));
			
			Collection<Hotel> values = hotelManage.values();
	
			for(Hotel hotel: values) {
				oos.writeObject(hotel); // 직렬화
			}
			
			System.out.println("Data Saved...");
			oos.close();
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
			
		System.exit(0);
	}	
	


}

