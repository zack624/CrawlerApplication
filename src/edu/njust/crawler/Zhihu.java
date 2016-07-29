package edu.njust.crawler;

import java.util.List;

public class Zhihu {
	private String question;
	private String zhihuUrl;
	private String questionDesciption;
	private List answer;
	
	
	public Zhihu() {
		super();
	}

	
	public String getQuestionDesciption() {
		return questionDesciption;
	}
	public void setQuestionDesciption(String questionDesciption) {
		this.questionDesciption = questionDesciption;
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
	public List getAnswer() {
		return answer;
	}
	public void setAnswer(List answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Zhihu [question=" + question + ", zhihuUrl=" + zhihuUrl
				+ ", description=" + questionDesciption + ",answer=" + answer+"]";
	}
	
}
