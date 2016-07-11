package edu.njust.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaiduCrawler {
	
	public static String sentGet(String urlString){
		BufferedReader in = null;
		String webContent = "";
		try{
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));
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
	
	public static Set<String> regexFilter2(String input,String regexRule){
		Pattern pattern = Pattern.compile(regexRule);
		Matcher matcher = pattern.matcher(input);
		Set<String> resultSet = new HashSet<String>();
		while(matcher.find()){
			resultSet.add("http://pan.baidu.com/mbox/homepage?short=" + matcher.group(1));
		}
		return resultSet;
	}
	
	public static void main(String[] args) {
//		String baiduContent = sentGet("http://www.baidu.com");
//		System.out.println(baiduContent);
//		
//		String result = regexFilter(baiduContent, "src=\"(.+?)\"");
//		System.out.println(result);
		Set<String> tempSet = new HashSet<String>();
		Set<String> resultSet = new HashSet<String>();
		Set<String> userfulSet = new HashSet<String>();
		for(int i = 1 ; i < 3 ; i++){
			String tiebaSearch = sentGet("http://tieba.baidu.com/f/search/res?isnew=1&kw=&qw=homepage%3Fshort%3D&rn=10&un=&only_thread=0&sm=1&sd=&ed=&pn="+i);
//			System.out.println(tiebaSearch);
			
			tempSet = regexFilter2(tiebaSearch, "short=</em>(.......)");
			resultSet.addAll(tempSet);
		}
		for (String string : resultSet) {
			String inv = sentGet(string);
//			System.out.println(string);
			if(inv.contains("加入该群组")){
				userfulSet.add(string);
			}
		}
		if(userfulSet.isEmpty()){
			System.out.println("none!");
		}else{
			for (String string : userfulSet) {
				System.out.println(string);
			}
		}
		
	}
}
