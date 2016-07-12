package edu.njust.test;

import edu.njust.baidu.BaiduCrawler;

public class UrlTest {
	public static void main(String[] args) {
		String result = BaiduCrawler.sentGet("http://pan.baidu.com/mbox/homepage?short=pLzksZ5","UTF-8");
//		String result2 = BaiduCrawler.sentGet("http://pan.baidu.com/mbox/homepage?short=kUDVfwF","UTF-8");
//		String result3 = BaiduCrawler.sentGet("http://pan.baidu.com/mbox/homepage?short=pLzksZ5","UTF-8");
//		String result4 = BaiduCrawler.sentGet("http://pan.baidu.com/mbox/homepage?short=pLzksZ5","UTF-8");
//		String result5 = BaiduCrawler.sentGet("http://pan.baidu.com/mbox/homepage?short=pLzksZ5","UTF-8");
		System.out.println(result);
	}
}
