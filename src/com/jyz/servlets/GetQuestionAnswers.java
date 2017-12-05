package com.jyz.servlets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyz.Daos.AnswerDaoImp;
import com.jyz.Daos.ScoreChangeDaoImp;
import com.jyz.beans.AnswerBean;
import com.jyz.beans.ScoreChangeBean;
import com.jyz.servlets.base.BaseServletFactory;

import net.sf.json.JSONArray;

/**
 * 获取问题的所有答案
 * @author Songzhihang
 *
 */
public class GetQuestionAnswers extends BaseServletFactory {
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String questionId = request.getParameter("questionId");
		Map<String, String> map = new HashMap<String, String>();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		List<AnswerBean> answerList = answerDaoImp.getDataList(Integer.parseInt(questionId));
		if (answerList != null) {
			JSONArray itemJson = JSONArray.fromObject(answerList);
			map.put("result", "success");
			map.put("data", itemJson.toString());
		} else {
			map.put("result", "fail");
			map.put("data", "[]");
		}
		return map;
	}
}