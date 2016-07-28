package edu.njust.crawler;

public class Zhihu {
	private String question;
	private String zhihuUrl;
	private String answer;
	
	
	public Zhihu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Zhihu(String question, String zhihuUrl, String answer) {
		super();
		this.question = question;
		this.zhihuUrl = zhihuUrl;
		this.answer = answer;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getZhihuUrl() {
		return zhihuUrl;
	}
	public void setZhihuUrl(String zhihuUrl) {
		this.zhihuUrl = zhihuUrl;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Zhihu [question=" + question + ", zhihuUrl=" + zhihuUrl
				+ ", answer=" + answer + "]";
	}
	
}
