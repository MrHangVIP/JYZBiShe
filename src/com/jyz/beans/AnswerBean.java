package com.jyz.beans;

import java.io.Serializable;

public class AnswerBean implements Serializable{
	private int answerId;

	private int questionId;

	private int questionnaireId;

	private int userId;

	private String answer;

	private String createTime;

	private int selectionId;

	private long createTimeStmp;// 时间戳

	private String type;// 问题类型

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(int questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}

	public long getCreateTimeStmp() {
		return createTimeStmp;
	}

	public void setCreateTimeStmp(long createTimeStmp) {
		this.createTimeStmp = createTimeStmp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
