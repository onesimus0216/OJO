
public class Ranking2 {

	public static void main(String[] args) {
		
		int[] score = {80, 95, 70, 100, 94};
		int[] rank = new int[score.length];
		for (int i=0; i<rank.length; i++) {
			rank[i] = 1;
		}
		
		for (int i=0; i<score.length-1; i++) {
			for (int j=i+1; j<score.length; j++) {
				
//				i번째 점수가 크면 j번째 석차를 증가시키고 j번째 점수가 크면 i번째 석차를 증가시킨다.
				if (score[i] > score[j]) {
					rank[j]++;
				} else if (score[i] < score[j]) {
					rank[i]++;
				}
				
			}
		}

		for (int i=0; i<score.length; i++) {
			System.out.printf("%3d점은 %d등 입니다.", score[i], rank[i]);
			for (int j=0; j<score[i]/10; j++) {
				System.out.print("★");
			}
			if (score[i] % 10 >= 5) {
				System.out.print("☆");
			}
			System.out.println();
		}

	}
	
}






