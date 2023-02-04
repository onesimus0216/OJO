import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Lotto2 {

	public static void main(String[] args) {
		
		Random random = new Random();
		int[] lotto = new int[45];
		for (int i=0; i<lotto.length; i++) {
			lotto[i] = i + 1;
		}
		
//		복권 구매 금액을 입력받는다.
		Scanner scanner = new Scanner(System.in);
		System.out.print("복권 구매 금액을 입력하세요: ");
		int money = scanner.nextInt();

//		입력한 금액만큼 반복하며 자동 로또 번호를 생성한다.
		for (int i=0; i<money/1000; i++) {
			
//			섞는다.
			for (int j=0; j<1000000; j++) {
				int r = random.nextInt(44) + 1;
				int temp = lotto[0];
				lotto[0] = lotto[r];
				lotto[r] = temp;
			}
			
//			정렬한다.
			int[] lottoNumber = new int[6];
			for (int j=0; j<6; j++) {
				lottoNumber[j] = lotto[j];
			}
//			Arrays.sort(lottoNumber);
			for (int j=0; j<lottoNumber.length-1; j++) {
				for (int k=j+1; k<lottoNumber.length; k++) {
					if (lottoNumber[j] > lottoNumber[k]) {
						int temp = lottoNumber[j];
						lottoNumber[j] = lottoNumber[k];
						lottoNumber[k] = temp;
					}
				}
			}
			
//			자동 로또 번호를 출력한다.
			System.out.printf("%3d game: ", i + 1);
			for (int j=0; j<6; j++) {
				System.out.printf("%02d ", lottoNumber[j]);
			}
			System.out.println();
			
			if ((i + 1) % 5 == 0) {
				System.out.println("===========================");
			}
			
		}
		
	}
	
}








