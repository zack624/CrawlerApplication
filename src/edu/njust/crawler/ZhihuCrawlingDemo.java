package edu.njust.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhihuCrawlingDemo {
	public static void main(String[] args) {
		String htmlContent = CrawlerUtils.sentGet("http://www.zhihu.com/explore/recommendations",
				"utf-8");
		
		List<Zhihu> zhihuList = getListRegexFilter(htmlContent);

		String testQ = CrawlerUtils.sentGet("http://www.zhihu.com/question/31491796", "utf-8");
		Zhihu z = new Zhihu();
		getListAnswer(testQ,z);
		System.out.println(z.getQuestionDesciption());
		System.out.println(z.getAnswer().size());
		
//		for (Zhihu zhihu : zhihuList) {
//			int index = zhihu.getZhihuUrl().indexOf("answer") - 1;
//			String url = zhihu.getZhihuUrl().substring(0,index);
//			
//			String answerPage = CrawlerUtils.sentGet(url,"utf-8");
//			getListAnswer(answerPage, zhihu);
//		}
//		System.out.println(zhihuList);
		
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
	
	public static void getListAnswer(String input,Zhihu zhihu){
		
		
		Pattern pdescription = Pattern.compile("\">.+?<div class=\"zm-editable-content\">(.+?)</div");
		Matcher mdescription = pdescription.matcher(input);
		if(mdescription.find()){
			String description = mdescription.group(1);
			zhihu.setQuestionDesciption(description);
		}
		
		List answers = new ArrayList();
		
		Pattern panswer = Pattern.compile("zm-editable-content clearfix\">(.+?)</div");
		Matcher manswer = panswer.matcher(input);
		while(manswer.find()){
			String answer = manswer.group(1);
			answers.add(answer);
		}
		zhihu.setAnswer(answers);
		
	}
}
