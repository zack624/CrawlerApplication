package edu.njust.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaiduCrawler {
	
	public static String sentGet(String urlString){
		BufferedReader in = null;
		String webContent = "";
		try{
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while((line = in.readLine()) != null){
				webContent += line;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return webContent;
	}
	
	public static String regexFilter(String input,String regexRule){
		Pattern pattern = Pattern.compile(regexRule);
		Matcher matcher = pattern.matcher(input);
		String result = "";
		if(matcher.find()){
			result = matcher.group(1);
		}
		return result;
	}
	
	public static void main(String[] args) {
		String baiduContent = sentGet("http://www.baidu.com");
		System.out.println(baiduContent);
		
		String result = regexFilter(baiduContent, "src=\"(.+?)\"");
		System.out.println(result);
	}
}
