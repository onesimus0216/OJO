package com.tjoeun.genericTest2;

public class ThreeDPrinterTest {

	public static void main(String[] args) {
		
		GenericPrinter<Powder> genericPrinter = new GenericPrinter<>();
		genericPrinter.setMaterial(new Powder());
		System.out.println(genericPrinter.getMaterial());
		Powder powder = genericPrinter.getMaterial();
		
		GenericPrinter<Plastic> genericPrinter2 = new GenericPrinter<>();
		genericPrinter2.setMaterial(new Plastic());
		System.out.println(genericPrinter2.getMaterial());
		Plastic plastic = genericPrinter2.getMaterial();
		System.out.println("===========================");
		
//		Water 클래스는 Material 클래스를 상속받지 않았으므로 GenericPrinter 클래스의
//		제네릭으로 넘길 수 없다.
//		GenericPrinter<Water> waterPrinter = new GenericPrinter<>(); // 에러 발생
		
	}
	
}








