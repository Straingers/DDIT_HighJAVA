import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int cnt = 1;
		int result = 1;
		while(true) {
			if(x == 1) {
				System.out.println(1);
				break;
			} else if(x <= 7) {
				System.out.println(2);
				break;
			} else if(x <= result) {
				System.out.println(cnt);
				break;
			}
			result += (6 * cnt);
			cnt++;
		}
	}
}
