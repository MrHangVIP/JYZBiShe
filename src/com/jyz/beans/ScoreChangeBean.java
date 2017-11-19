package com.jyz.beans;

import java.io.Serializable;

/**
 * 积分变动实体
 * @author Songzhihang
 *
 */
public class ScoreChangeBean implements Serializable{
	
	    private int id;
	    
	    private int userId;

	    private int questionnaireId;

	    private String createtime;

	    private String action;

	    private int score_change;

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public int getQuestionnaireId() {
	        return questionnaireId;
	    }

	    public void setQuestionnaireId(int questionnaireId) {
	        this.questionnaireId = questionnaireId;
	    }

	    public String getCreatetime() {
	        return createtime;
	    }

	    public void setCreatetime(String createtime) {
	        this.createtime = createtime;
	    }

	    public String getAction() {
	        return action;
	    }

	    public void setAction(String action) {
	        this.action = action;
	    }

	    public int getScore_change() {
	        return score_change;
	    }

	    public void setScore_change(int score_change) {
	        this.score_change = score_change;
	    }

}
