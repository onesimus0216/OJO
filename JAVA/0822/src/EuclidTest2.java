import java.util.Scanner;

public class EuclidTest2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("숫자 2개를 입력하세요: ");
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int r = 1;
		
		int high, low;
		if (a > b) {
			high = a;
			low = b;
		} else {
			low = a;
			high = b;
		}

		while (r > 0) {
			r = high % low;
			high = low;
			low = r;
		}
		
		int l = a * b / high;
		System.out.println("최대공약수: " + high + ", 최소공배수: " + l);
		
	}
	
}






