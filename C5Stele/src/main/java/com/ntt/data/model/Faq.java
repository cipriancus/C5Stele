package com.ntt.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Faqs")
public class Faq extends BaseAuditEntity {
	
	@Column(name = "QUESTION")
	private String question;

	@Column(name = "ANSWER")
	private String answer;

	@Column(name = "ORDER_OF_QUESTIONS")
	private Integer order_of_questions;
	

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getOrder_of_questions() {
		return order_of_questions;
	}

	public void setOrder_of_questions(Integer order_of_questions) {
		this.order_of_questions = order_of_questions;
	}
	
	@Override
	public String toString() {
		return "Faq [question=" + question + ", answer=" + answer + ", order_of_questions=" + order_of_questions + "]";
	}
}
