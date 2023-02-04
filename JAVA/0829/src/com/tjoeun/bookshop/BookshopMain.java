package com.tjoeun.bookshop;

import java.util.Date;

public class BookshopMain {

	public static void main(String[] args) {
		
		BookVO book1 = new BookVO("java", "홍길동", "더조은출판사", new Date(2020, 7, 25), 1029.99);
		BookVO book2 = new BookVO("java", "홍길자", "더조은출판사", new Date(2020, 7, 25), 1029.99);
		BookVO book3 = new BookVO("java", "홍길숙", "더조은출판사", new Date(2020, 7, 25), 1029.99);
		BookVO book4 = new BookVO("java", "홍길희", "더조은출판사", new Date(2020, 7, 25), 1029.99);
		BookVO book5 = new BookVO("java", "홍길도", "더조은출판사", new Date(2020, 7, 25), 1029.99);
		BookVO book6 = new BookVO("java", "홍길자", "더조은출판사", new Date(2020, 7, 25), 1029.99);
		
		BookList bookList = new BookList();
		
		bookList.addBook(book1);
		bookList.addBook(book2);
		bookList.addBook(book3);
		bookList.addBook(book4);
		bookList.addBook(book5);
		bookList.addBook(book6);
		
		System.out.println(bookList);
		
	}
	
}












