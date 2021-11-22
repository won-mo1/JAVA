package kr.or.cnu.controller;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class Test {
	public static void main(String[] args) {
		
		String name = "PJ8.sql";
		int index = name.split("\\.").length;
		System.out.println(index);
		String last = name.split("[.]")[index-1];  
		System.out.println(last);
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid +"-"+ name);
		System.out.println(uuid +"-"+ name);
		
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
