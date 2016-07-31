package edu.njust.zhihu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.njust.utils.CrawlerUtils;
import edu.njust.utils.FileUtils;

/**
 * 知乎爬虫
 * 爬取知乎推荐编辑页上所有问题，并通过url访问其所有答案，最后存储到本地
 * from http://blog.csdn.net/pleasecallmewhy/article/details/17594303
 * @author zack
 *
 */
public class ZhihuCrawlingDemo {
	public static void main(String[] args) {
		String htmlContent = CrawlerUtils.sentGet("http://www.zhihu.com/explore/recommendations",
				"utf-8");
		
		List<Zhihu> zhihuList = getListRegexFilter(htmlContent);
		for (Zhihu zhihu : zhihuList) {
			String answerPage = CrawlerUtils.sentGet(zhihu.getZhihuUrl(), "utf-8");
			getListAnswer(answerPage, zhihu);
		}
		StringBuffer listSb = new StringBuffer();
		int i = 1;
		for (Zhihu zhihu : zhihuList) {
			listSb.append("[" + (i++) + "]\n");
			listSb.append(zhihu.outputFormat());
		}
		FileUtils.writeIntoFile("D:/zhihu/zhihu.txt",listSb.toString(),false);
		System.out.println("success");
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
			if(url.contains("question")){
				int index = url.indexOf("answer") - 1;
				String shortUrl = url.substring(0,index);
				
				zhihu.setQuestion(question);
				zhihu.setZhihuUrl("http://www.zhihu.com" + shortUrl);
				zhihuList.add(zhihu);
			}
			
		}
		return zhihuList;
	}
	
	public static void getListAnswer(String input,Zhihu zhihu){
		//正则表达示中用*是防止没有问题描述
		//?表示懒惰模式，一旦匹配到第一个符合的就成功
		Pattern pdescription = Pattern.compile("zh-question-detail.+?<div.+?>(.*?)</div");
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
