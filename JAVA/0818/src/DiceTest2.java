import java.util.Random;

public class DiceTest2 {

	public static void main(String[] args) {
		
		Random random = new Random();
//		int num1 = 0, num2 = 0, num3 = 0, num4 = 0, num5 = 0, num6 = 0;
//		주사위 눈의 개수를 기억할 배열을 선언만 한다. => 모두 0으로 자동 초기화된다.
//		count[0] => 1의 개수, count[1] => 2의 개수, ..., count[5] => 6의 개수
		int[] count = new int[6];
		
		for (int i=0; i<10; i++) {
			int dice = random.nextInt(6) + 1;
			System.out.println(dice);
			
//			switch (dice) {
//				case 1:
//					count[0]++; break;
//				case 2:
//					count[1]++; break;
//				case 3:
//					count[2]++; break;
//				case 4:
//					count[3]++; break;
//				case 5:
//					count[4]++; break;
//				case 6:
//					count[5]++; break;
//			}
			count[dice - 1]++;
		}
		
//		System.out.println("1의 개수: " + count[0]);
//		System.out.println("2의 개수: " + count[1]);
//		System.out.println("3의 개수: " + count[2]);
//		System.out.println("4의 개수: " + count[3]);
//		System.out.println("5의 개수: " + count[4]);
//		System.out.println("6의 개수: " + count[5]);
		
		for (int i=0; i<count.length; i++) {
			System.out.println(i + 1 + "의 개수: " + count[i]);
		}
		
	}
	
}















