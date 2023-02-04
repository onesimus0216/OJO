import java.util.Scanner;

public class JuminNoTest4 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("주민등록번호 13자리를 '-'없이 입력하세요: ");
		String jumin = scanner.nextLine().trim();

//		String check = "234567892345";
		int sum = 0;
		for (int i=0; i<12; i++) {
//			sum += Integer.parseInt(jumin.charAt(i) + "") * Integer.parseInt(check.charAt(i) + "");
//			sum += Integer.parseInt(jumin.charAt(i) + "") * (i < 8 ? i + 2 : i - 6);
			sum += Integer.parseInt(jumin.charAt(i) + "") * (i % 8 + 2);
		}
		
		int result = (11 - sum % 11) % 10;
		System.out.println(result == jumin.charAt(12) - 48 ? "정상" : "오류");

	}
	
}










