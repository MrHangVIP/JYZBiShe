package com.jyz.servlets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyz.Daos.QuestionDaoImp;
import com.jyz.Daos.QuestionnaireDaoImp;
import com.jyz.Daos.ScoreDaoImp;
import com.jyz.Daos.SelectionDaoImp;
import com.jyz.Daos.UserDaoImp;
import com.jyz.beans.QuestionBean;
import com.jyz.beans.QuestionnaireBean;
import com.jyz.beans.ScoreBean;
import com.jyz.beans.SelectionBean;
import com.jyz.beans.UserBean;
import com.jyz.servlets.base.BaseServletFactory;

public class CreateQuestionnaire extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		QuestionnaireBean questionnaireBean = new QuestionnaireBean();
		questionnaireBean.setTitle(request.getParameter("title"));
		questionnaireBean.setThanks(request.getParameter("thanks"));
		questionnaireBean.setIntroduce(request.getParameter("introduce"));
		questionnaireBean.setNickName(request.getParameter("nickname"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		questionnaireBean.setUserId(userId);
		List<QuestionBean> questionList = new ArrayList<>();
		Map<String, String[]> params = request.getParameterMap();
		for (int i = 0; i < params.keySet().size() - 4; i++) {
			QuestionBean questionBean = new QuestionBean();
			for (String key : params.keySet()) {
				if (key.equals("title" + i)) {
					questionBean.setTitle(request.getParameter(key));
				}
				if (key.equals("type" + i)) {
					questionBean.setType(request.getParameter(key));
				}
				if (key.equals("lines" + i)) {
					questionBean.setLine(request.getParameter(key));
				}
				if (key.equals("least" + i)) {
					questionBean.setLeast(request.getParameter(key));
				}
				if (key.equals("more" + i)) {
					questionBean.setMore(request.getParameter(key));
				}
				if (key.equals("isMust" + i)) {
					questionBean.setIsMust(request.getParameter(key));
				}
			}
			if (questionBean.getTitle() == null) {// 沒有了
				i = params.size();// 退出循環
			} else {
				if (!"3".equals(questionBean.getType())) {// 添加選項
					List<SelectionBean> selectionList = new ArrayList<>();
					for (int j = 0; j < params.size(); j++) {
						SelectionBean bean = new SelectionBean();
						for (String key : params.keySet()) {
							if (key.equals(i + "title" + j)) {
								bean.setTitle(request.getParameter(key));
							}
							if (key.equals(i + "isSelect" + j)) {
								bean.setIsSelect(request.getParameter(key));
							}

						}
						if (bean.getTitle() == null) {
							j = params.size();// 退出循環
						} else {
							bean.setOrder(j + "");
							selectionList.add(bean);
						}
					}
					questionBean.setSelectionItemList(selectionList);
				}
				questionList.add(questionBean);
			}
		}
		questionnaireBean.setQuestionItemList(questionList);
		Map<String, String> map = new HashMap<String, String>();
		QuestionnaireDaoImp questionnaireDaoImp = new QuestionnaireDaoImp();
		int questionnaireId = questionnaireDaoImp.insertDatas(questionnaireBean);
		boolean isSuccess=true;
		if (questionnaireId > 0) {
			for (int i = 0; i < questionnaireBean.getQuestionItemList().size(); i++) {
				QuestionBean questionBean = questionnaireBean.getQuestionItemList().get(i);
				questionBean.setQuestionnaireId(questionnaireId);
				QuestionDaoImp questionDaoImp = new QuestionDaoImp();
				int questionId = questionDaoImp.insertDatas(questionBean);
				if (questionId > 0) {
					if (!"3".equals(questionBean.getType())) {// 添加選項
						for (int j = 0; j < questionBean.getSelectionItemList().size(); j++) {
							SelectionBean selectionBean = questionBean.getSelectionItemList().get(j);
							selectionBean.setQuestionId(questionId);
							SelectionDaoImp selectionDaoImp = new SelectionDaoImp();
							isSuccess = selectionDaoImp.insertData(selectionBean);
							if(!isSuccess){
								i= questionnaireBean.getQuestionItemList().size();
								j=questionBean.getSelectionItemList().size();
							}
						}
					}
				} else {
					i= questionnaireBean.getQuestionItemList().size();
					isSuccess=false;
				}
			}
		} else {
			isSuccess=false;
		}
		if(isSuccess){
//			更新積分
			ScoreDaoImp scoreDaoImp=new ScoreDaoImp();
			scoreDaoImp.updateData(false,userId,questionnaireId);
			map.put("result", "success");
			map.put("data", "createSuccess");
		}else{
			map.put("result", "fail");
			map.put("data", "sqlFial");
		}
		return map;
	}

}
