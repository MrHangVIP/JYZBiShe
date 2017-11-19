package com.jyz.beans;

import java.io.Serializable;
import java.util.List;

public class QuestionnaireBean implements Serializable{

	private int questionnaireId;

	private int userId;

	private String title;

	private String introduce;// 问卷说明

	private String thanks;// 感谢语

	private List<QuestionBean> questionItemList;// 问卷题目

	private String createTime;

	private String updateTime;

	private String finishTime;// 截止时间

	private long createTimeStmp;// 时间戳

	private long finishTimeStmp;

	private String nickName;// 创建名字

	private int answerNum;// 回答数量

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getThanks() {
		return thanks;
	}

	public void setThanks(String thanks) {
		this.thanks = thanks;
	}

	public List<QuestionBean> getQuestionItemList() {
		return questionItemList;
	}

	public void setQuestionItemList(List<QuestionBean> questionItemList) {
		this.questionItemList = questionItemList;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public long getCreateTimeStmp() {
		return createTimeStmp;
	}

	public void setCreateTimeStmp(long createTimeStmp) {
		this.createTimeStmp = createTimeStmp;
	}

	public long getFinishTimeStmp() {
		return finishTimeStmp;
	}

	public void setFinishTimeStmp(long finishTimeStmp) {
		this.finishTimeStmp = finishTimeStmp;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

}
