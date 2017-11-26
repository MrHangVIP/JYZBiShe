package com.jyz.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jyz.Daos.base.BaseDBFactor;
import com.jyz.beans.AnswerBean;
import com.jyz.beans.QuestionBean;
import com.jyz.beans.QuestionnaireBean;
import com.jyz.beans.SelectionBean;
import com.jyz.utils.DateUtil;

public class AnswerDaoImp extends BaseDBFactor<AnswerBean> {

	@Override
	public boolean insertData(AnswerBean t) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "insert into t_answer(questionId, questionnaireId,userId,answer,createTime,createTimeStmp,selectionId)"
					+ " value(?,?,?,?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 设置值
			stat.setInt(1, t.getQuestionId());
			stat.setInt(2, t.getQuestionnaireId());
			stat.setInt(3, t.getUserId());
			stat.setString(4, t.getAnswer());
			stat.setString(5, DateUtil.getCurrentDate());
			stat.setLong(6, t.getCreateTimeStmp());
			stat.setInt(7, t.getSelectionId());
			// 执行
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if (rowCount > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<AnswerBean> getDataList(Object... obj) {
		int questionnaireId = (int) obj[0];
		int userId = (int) obj[1];
		Connection conn = null;
		List<AnswerBean> questionBeans = null;
		try {
			conn = getConn();
			QueryRunner qr = new QueryRunner();
			String sql = "select * from t_answer where questionnaireId = ? and userId= ?";
			questionBeans = (List<AnswerBean>) qr.query(conn, sql,
					new BeanListHandler<AnswerBean>(AnswerBean.class), questionnaireId,userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(null, conn);
		}
		return questionBeans;
	}

	@Override
	public AnswerBean getData(Object... obj) {
		return null;
	}

	@Override
	public boolean updateData(Object... obj) {
		return false;
	}

	@Override
	public boolean deleteData(int id) {
		return false;
	}

}
