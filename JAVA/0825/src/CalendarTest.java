import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CalendarTest {

//	메소드의 형식, []로 묶는 내용은 생략이 가능하다.
//	[접근 권한 지정자] [static] 리턴타입 메소드이름() { // 메소드의 머리
//		메소드가 실행할 문장;
//		...;
//		[return 값;]
//	} // {} 블록을 메소드의 몸통이라 한다.
	
//	접근 권한 지정자
//	private: 현재 클래스 외부에서 접근할 수 없다.
//	protected: 현재 클래스와 현재 클래스를 상속받은 자식 클래스에서만 접근할 수 있다.
//	package: 접근 권한 지정자 생략시, 같은 패키지에서는 public 처럼 사용되고 다른 패키지에서는
//	         private 처럼 사용된다.
//	public: 현재 클래스 내부, 외부 어디에서나 자유롭게 접근할 수 있다.
	
//	static(정적) 메소드는 메소드를 작성한 클래스의 객체를 생성하지 않고 클래스 이름에 "."을 찍어서
//	실행할 수 있다. => 자주 사용하는 메소드들은 정적 메소드로 만들어 사용하면 편리하다.
	
//	리턴 타입은 메소드가 실행되고 난 후 결과를 되돌려줄 때 결과의 자료형을 적어준다.
//	메소드를 실행한 후 결과를 되돌려줄 필요가 없으면 return을 생략할 수 있고 return을 생략하면
//	리턴 타입에는 "void"라고 적어준다.
	
//	년도를 인수로 넘겨받아 윤년, 평년을 판단해 윤년이면 true, 평년이면 false를 리턴하는 메소드
//	논리값을 기억하는 변수나 논리값을 리턴하는 메소드의 이름은 "is"로 시작하는 것이 관행이다.
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}
	
//	년, 월을 인수로 넘겨받아 그 달의 마지막 날짜를 리턴하는 메소드
	public static int lastDay(int year, int month) {
		int[] m = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		m[1] = isLeapYear(year) ? 29 : 28;
		return m[month - 1];
	}
	
//	년, 월, 일을 인수로 넘겨받아 1년 1월 1일 부터 지난 날짜를 계산해 리턴하는 메소드
	public static int totalDay(int year, int month, int day) {
		int sum = (year - 1) * 365 + (year - 1) / 4 - (year - 1) / 100 + (year - 1) / 400;
		for (int i=1; i<month; i++) {
			sum += lastDay(year, i);
		}
		return sum + day;
	}
	
//	년, 월, 일을 인수로 넘겨받아 요일 숫자로 계산해 리턴하는 메소드
//	일요일(0), 월요일(1), 화요일(2), 수요일(3), 목요일(4), 금요일(5), 토요일(6)
	public static int weekDay(int year, int month, int day) {
		return totalDay(year, month, day) % 7;
	}
	
//	static은 자바 프로그램이 실행하기 전에 static으로 선언된 함수(메소드)나 변수를 메모리에
//	올려준다.
//	main 메소드가 실행되기 위해서는 메모리에 적재되어야 한다.
//	main 메소드가 메모리에 적재되어있지 않으면 프로그램의 시작점인 main() 메소드를 호출하려는데
//	메모리에 main() 메소드가 없으므로 실행을 할 수 없다.
//	그래서 main() 메소드는 누군가 호출하기 전에 자바 가상머신이 메모리에 적재시킨다.
	public static void main(String[] args) {
		
//		static 메소드는 static 메소드에만 접근할 수 있다.
//		System.out.println(isLeapYear(2022));
//		System.out.println(lastDay(2022, 8));
//		System.out.println(totalDay(2022, 8, 25));
//		System.out.println(weekDay(2022, 8, 25));
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("이번달(1), 특정달(2): ");
		int confirm = scanner.nextInt();
		
		int year, month;
		if (confirm == 1) {
			Date date = new Date();
			year = date.getYear() + 1900;
			month = date.getMonth() + 1;
		} else {
			System.out.print("달력을 출력할 년, 월을 입력하세요: ");
			year = scanner.nextInt();
			month = scanner.nextInt();
		}
		
		System.out.println("============================");
		System.out.printf("         %4d년%2d월\n", year, month);
		System.out.println("============================");
		System.out.println(" 일  월  화  수  목  금  토 ");
		System.out.println("============================");
	
//		1일이 출력될 위치를 맞추기 위해 1일의 요일만큼 반복하며 빈 칸(날짜당 4칸)을 출력한다.
//		for (int i=1; i<=weekDay(year, month, 1); i++) {
//			System.out.print("    ");
//		}
		
//		1일이 출력될 위치를 맞추기 위해 1일의 요일만큼 반복하며 전달 날짜를 출력한다.
		int week = weekDay(year, month, 1);
		int start = 0;
		if (month == 1) {
//			start = lastDay(year - 1, 12) - week; // 1월
			start = 31 - week; // 1월
		} else {
			start = lastDay(year, month - 1) - week; // 2 ~ 12월
		}
		for (int i=1; i<=week; i++) {
			System.out.printf(" %2d ", ++start);
		}
		
//		1일부터 달력을 출력할 달의 마지막 날짜까지 반복하며 날짜를 출력한다.
		for (int i=1; i<=lastDay(year, month); i++) {
			System.out.printf(" %2d ", i);
//			출력한 날짜(i)가 토요일이고 그 달의 마지막 날짜가 아니면 줄을 바꾼다.
			if (weekDay(year, month, i) == 6 && i != lastDay(year, month)) {
				System.out.println();
			}
		}
		
//		날짜를 다 출력하고 남은 빈 칸에 다음달 1일의 요일부터 토요일까지 반복하며 날짜를 출력한다.
//		if (month == 12) {
//			week = weekDay(year + 1, 1, 1); // 12월
//		} else {
//			week = weekDay(year, month + 1, 1); // 1 ~ 11월
//		}
//		if (week != 0) {
//			start = 0;
//			for (int i=week; i<=6; i++) {
//				System.out.printf(" %2d ", ++start);
//			}
//		}
		
		week = weekDay(year, month, lastDay(year, month)) + 1;
		start = 0;
		for (int i=week; i<=6; i++) {
			System.out.printf(" %2d ", ++start);
		}
		
		System.out.println("\n============================");
		
	}
	
}
























