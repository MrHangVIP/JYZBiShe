package com.jyz.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyz.Daos.AnswerDaoImp;
import com.jyz.Daos.ScoreDaoImp;
import com.jyz.beans.AnswerBean;
import com.jyz.servlets.base.BaseServletFactory;

public class CreateAnswer extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<AnswerBean> answerList=new ArrayList<>();
		Map<String, String[]> params = request.getParameterMap();
		int userId=0,questionnaireId=0;
		for (int i = 0; i < params.keySet().size(); i++) {
			AnswerBean answerBean = new AnswerBean();
			for (String key : params.keySet()) {
				if (key.equals("questionnaireId" + i)) {
					answerBean.setQuestionnaireId(Integer.parseInt((request.getParameter(key))));
					questionnaireId=Integer.parseInt((request.getParameter(key)));
				}
				if (key.equals("questionId" + i)) {
					answerBean.setQuestionId(Integer.parseInt((request.getParameter(key))));
				}
				if (key.equals("userId" + i)) {
					answerBean.setUserId(Integer.parseInt((request.getParameter(key))));
					userId=Integer.parseInt((request.getParameter(key)));
				}
				if (key.equals("selectionId" + i)) {
					answerBean.setSelectionId(Integer.parseInt((request.getParameter(key))));
				}
				if (key.equals("type" + i)) {
					answerBean.setType(request.getParameter(key));
				}
				if (key.equals("answer" + i)) {
					answerBean.setAnswer(request.getParameter(key));
				}
			}
			if(answerBean.getType()!=null){
				answerList.add(answerBean);
			}else{
				i=params.size();
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		boolean isSuccess=true;
		for(AnswerBean bean:answerList){
			AnswerDaoImp answerDaoImp = new AnswerDaoImp();
			isSuccess= answerDaoImp.insertData(bean)&&isSuccess;
		}
		if(isSuccess){
//			更新積分
			ScoreDaoImp scoreDaoImp=new ScoreDaoImp();
			scoreDaoImp.updateData(true,userId,questionnaireId);
			map.put("result", "success");
			map.put("data", "AnswerSuccess");
		}else{
			map.put("result", "fail");
			map.put("data", "sqlFial");
		}
		return map;
	}
}