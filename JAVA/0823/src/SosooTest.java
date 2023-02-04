import java.util.Scanner;

public class SosooTest {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("소수인가 판단할 숫자를 입력하세요: ");
		int n = scanner.nextInt();
		
//		모든 수는 1로 나눠 떨어지기 때문에 2부터 떨어질 때 까지 나눠본다.
		int i;
		for (i=2; i<=n; i++) {
			if (n % i == 0) {
//				나눠서 떨어지면 소수인가 판단하기 위해서 반복을 탈출한다.
				break;
			}
		}
		
//		소수인가 판단한다.
		if (n == i) {
			System.out.println(n + "은(는) 소수입니다.");
		} else {
			System.out.println(n + "은(는) 소수가 아닙니다.");
		}
		
//		조건식 ? 참일 때 실행할 문장 : 거짓일 때 실행할 문장;
		System.out.println(n + "은(는) 소수" + (n == i ? "입니다." : "가 아닙니다."));
		
	}
	
}




