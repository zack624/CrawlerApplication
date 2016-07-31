package edu.njust.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtils {
	public static boolean createFile(String filePath){
		boolean isSuccess = false;
		
		//先处理filePath的目录
		int index = filePath.lastIndexOf("/");
		String dirPath = filePath.substring(0,index);
		File dirFile = new File(dirPath);
		isSuccess = dirFile.mkdir();
		
		File file = new File(filePath);
		try {
			isSuccess = file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	public static boolean writeIntoFile(String filePath,String content,boolean isAppend){
		boolean flag = false;

		File file = new File(filePath);
		if(!file.exists()){
			createFile(filePath);
		}
		FileWriter fw = null;
		try {
//			OutputStream out = new FileOutputStream(new File(filePath));
//			out.write(content.getBytes());
//			out.flush();
			fw = new FileWriter(new File(filePath),isAppend);
			fw.write(content);
			fw.flush();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fw != null){
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return flag;
	}
}
