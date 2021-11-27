package kr.or.board.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetLogger {

	public void getLog(Class class1, String logInfo) {
		
		String path = "D:\\2021\\log";
		String className= class1.getSimpleName();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-");
		String today = format.format(date);
		
		File file = new File(path, today + "log.txt");
		format = new SimpleDateFormat("HH:mm:ss");
		String now = format.format(date);
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write("\n" + className + "//" + now + ">>" + logInfo);
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
