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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetQuestionnaireList extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		QuestionnaireDaoImp questionnaireDaoImp = new QuestionnaireDaoImp();
		List<QuestionnaireBean> questionnaireBeanList=questionnaireDaoImp.getDataList();
		if(questionnaireBeanList!=null){
			for(int i=0;i<questionnaireBeanList.size();i++){
				QuestionnaireBean questionnaireBean= questionnaireBeanList.get(i);
				QuestionDaoImp questionDaoImp = new QuestionDaoImp();
				List<QuestionBean> questionBeanList=questionDaoImp.getDataList(questionnaireBean.getQuestionnaireId());
				if(questionBeanList!=null){
					for(int j=0;j<questionBeanList.size();j++){
						QuestionBean questionBean= questionBeanList.get(j);
						if(!"3".equals(questionBean.getType())){
							SelectionDaoImp selectionDaoImp = new SelectionDaoImp();
							List<SelectionBean> selectionBeanList=selectionDaoImp.getDataList(questionBean.getQuestionId());
							questionBean.setSelectionItemList(selectionBeanList);
						}
					}
					questionnaireBean.setQuestionItemList(questionBeanList);
				}
			}
			JSONArray itemJson = JSONArray.fromObject(questionnaireBeanList);
			map.put("result", "success");
			map.put("data",  itemJson.toString());
		}else{
			map.put("result", "fail");
			map.put("data", "[]");
		}
		return map;
	}

}
