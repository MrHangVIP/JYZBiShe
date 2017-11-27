package com.jyz.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyz.Daos.QuestionDaoImp;
import com.jyz.Daos.QuestionnaireDaoImp;
import com.jyz.Daos.SelectionDaoImp;
import com.jyz.beans.QuestionBean;
import com.jyz.beans.QuestionnaireBean;
import com.jyz.beans.SelectionBean;
import com.jyz.servlets.base.BaseServletFactory;

import net.sf.json.JSONArray;

public class GetQuestionnaireList extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		Map<String, String> map = new HashMap<String, String>();
		QuestionnaireDaoImp questionnaireDaoImp = new QuestionnaireDaoImp();
		List<QuestionnaireBean> questionnaireBeanList = null;
		if (userId != null) {
			String type = request.getParameter("type");
			if("publish".equals(type)){
				questionnaireBeanList = questionnaireDaoImp.getSomeOneDataList(Integer.parseInt(userId));
			}
			if("answer".equals(type)){
				questionnaireBeanList = questionnaireDaoImp.getAnswerList(Integer.parseInt(userId));
			}
		} else {
			questionnaireBeanList = questionnaireDaoImp.getDataList();
		}
		if (questionnaireBeanList != null) {
			for (int i = 0; i < questionnaireBeanList.size(); i++) {
				QuestionnaireBean questionnaireBean = questionnaireBeanList.get(i);
				QuestionDaoImp questionDaoImp = new QuestionDaoImp();
				List<QuestionBean> questionBeanList = questionDaoImp
						.getDataList(questionnaireBean.getQuestionnaireId());
				if (questionBeanList != null) {
					for (int j = 0; j < questionBeanList.size(); j++) {
						QuestionBean questionBean = questionBeanList.get(j);
						if (!"3".equals(questionBean.getType())) {
							SelectionDaoImp selectionDaoImp = new SelectionDaoImp();
							List<SelectionBean> selectionBeanList = selectionDaoImp
									.getDataList(questionBean.getQuestionId());
							questionBean.setSelectionItemList(selectionBeanList);
						}
					}
					questionnaireBean.setQuestionItemList(questionBeanList);
				}
			}
			JSONArray itemJson = JSONArray.fromObject(questionnaireBeanList);
			map.put("result", "success");
			map.put("data", itemJson.toString());
		} else {
			map.put("result", "fail");
			map.put("data", "[]");
		}
		return map;
	}

}
