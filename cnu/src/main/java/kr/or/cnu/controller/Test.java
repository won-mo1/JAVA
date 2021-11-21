package kr.or.cnu.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Test {
	public static void main(String[] args) {
		
		LocalDate now = LocalDate.now();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy\\MM\\dd");
		
		String path = now.format(format);
		
		String folderPath = "D:\\"+ path;
		File folder = new File(folderPath);
		
		if(!folder.exists()) {
			try {
				folder.mkdirs();
				System.out.println("폴더생성위치 : " + folderPath);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}else {
			System.out.println("폴더생성위치 : " + folderPath);
		}
		
	}
}
