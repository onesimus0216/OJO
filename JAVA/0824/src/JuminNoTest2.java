import java.util.Date;
import java.util.Scanner;

public class JuminNoTest2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("주민등록번호 13자리를 '-'없이 입력하세요: ");
		String jumin = scanner.nextLine().trim();

//		8304221185600 => 둘리 주민등록번호
//		System.out.println(jumin.substring(0, 2) + 1900); // 831900
//		System.out.println(jumin.charAt(0)); // 8
//		System.out.println(jumin.charAt(1)); // 3
//		int year = (jumin.charAt(0) - '0') * 10 + (jumin.charAt(1) - 48);
		
//		Integer.parseInt(): 인수로 지정된 문자열을 정수로 변환한다.
//		Double.parseDouble(): 인수로 지정된 문자열을 실수로 변환한다.
//		Boolean.parseBoolean(): 인수로 지정된 문자열을 논리값(true, false)으로 변환한다.
		int year = Integer.parseInt(jumin.substring(0, 2));
		
//		if (jumin.charAt(6) <= '2') {
//			year += 1900;
//		} else {
//			year += 2000;
//		}
		year += jumin.charAt(6) <= '2' ? 1900 : 2000;
		System.out.println(year);
		
//		컴퓨터의 날짜 데이터를 얻어와서 년도만 꺼낸다.
		Date date = new Date();
		System.out.println(date.getYear() + 1900); // 올해
		System.out.println(date.getYear() + 1900 - year); // 만 나이
		
	}
	
}









