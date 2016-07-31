package edu.njust.zhihu;

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
	
	public String outputFormat(){
		StringBuffer sb = new StringBuffer();
		
		sb.append("question:" + question + "\n");
		sb.append("description:" + questionDesciption + "\n");
		sb.append("url:" + zhihuUrl + "\n");
		for (int i = 0;i<answer.size();i++) {
			sb.append("answer" + (i+1) + ":" + answer.get(i) + "\n");
		}
		sb.append("\n**************************************\n\n");
		String format = sb.toString();
		format = format.replaceAll("<br>","\n         ");
		format = format.replaceAll("<.*?>","");
		return format;
	}
	
}
