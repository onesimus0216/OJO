
public class BitOperator {

	public static void main(String[] args) {
		
		int a = 10, b = 12; // 0000 1010
		
//		비트 연산자
//		&: 비트 and, 두 비트가 모두 1이면 1
//		|: 비트 or, 두 비트 중에서 1비트 이상 1이면 1
//		^: 비트 xor(배타적 논리합), 두 비트가 다를때 1
//		~: 비트 부정, 1은 0으로 0은 1로, 1의 보수를 계산한다.
		System.out.println(a & b); // 00001010 & 00001100 => 00001000 => 8
		System.out.println(a | b); // 00001010 | 00001100 => 00001110 => 14
		System.out.println(a ^ b); // 00001010 ^ 00001100 => 00000110 => 6
		
//		~00001010 => 11110101 => 부호 비트가 1이르모 음수이고 다시 2의 보수로 변환하면 => 00001011 => -11
		System.out.println(~a); // -11
		
	}
	
}
