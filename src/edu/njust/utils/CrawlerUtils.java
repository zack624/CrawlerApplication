package edu.njust.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerUtils {	
	public static String sentGet(String urlString,String code){
		BufferedReader in = null;
		String webContent = "";
		try{
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),code));
			String line;
			while((line = in.readLine()) != null){
				webContent += line;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return webContent;
	}
	
	public static String singleRegexFilter(String input,String regexRule){
		Pattern pattern = Pattern.compile(regexRule);
		Matcher matcher = pattern.matcher(input);
		String result = "";
		if(matcher.find()){
			result = matcher.group(1);
		}
		return result;
	}
	
	public static List listRegexFilter(String input,String regexRule){
		Pattern pattern = Pattern.compile(regexRule);
		Matcher matcher = pattern.matcher(input);
		List resultSet = new ArrayList();
		while(matcher.find()){
			resultSet.add(matcher.group(1));
		}
		return resultSet;
	}

}
