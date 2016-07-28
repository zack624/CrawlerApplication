package edu.njust.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhihuCrawlingDemo {
	public static void main(String[] args) {
		String htmlContent = CrawlerUtils.sentGet("http://www.zhihu.com/explore/recommendations",
				"utf-8");
		
		List zhihuList = getListRegexFilter(htmlContent);
		System.out.println(zhihuList);
	}
	
	public static List<Zhihu> getListRegexFilter(String input){
		List<Zhihu> zhihuList = new ArrayList<Zhihu>();
		
		Pattern pquest = Pattern.compile("question_link\".href=\".+?>(.+?)<");
		Matcher mquest = pquest.matcher(input);
		
		Pattern purl = Pattern.compile("question_link\".href=\"(.+?)\"");
		Matcher murl = purl.matcher(input);
		//这个判断可以去除问题和url不同时存在的项
		while(mquest.find() && murl.find()){
			Zhihu zhihu = new Zhihu();
			
			String question = mquest.group(1);
			String url = murl.group(1);
			
			zhihu.setQuestion(question);
			zhihu.setZhihuUrl("http://www.zhihu.com" + url);
			zhihuList.add(zhihu);
		}
		return zhihuList;
	}
}
