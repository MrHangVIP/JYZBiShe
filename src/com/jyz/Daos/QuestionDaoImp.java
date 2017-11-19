package com.jyz.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jyz.Daos.base.BaseDBFactor;
import com.jyz.beans.QuestionBean;
import com.jyz.beans.QuestionnaireBean;
import com.jyz.beans.ScoreChangeBean;
import com.jyz.utils.DateUtil;

public class QuestionDaoImp extends BaseDBFactor<QuestionBean> {

	@Override
	public boolean insertData(QuestionBean t) {
		return false;
	}

	@Override
	public List<QuestionBean> getDataList(Object... obj) {
		int qusetionnaireId=(int)obj[0];
		Connection conn=null;
		List<QuestionBean> questionBeans=null;
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			String sql="select * from t_question where questionnaireId = ?";
			questionBeans=(List<QuestionBean>)qr.query(conn,sql,new BeanListHandler<QuestionBean>(QuestionBean.class),qusetionnaireId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn);
		}
		return questionBeans;
	}

	@Override
	public QuestionBean getData(Object... obj) {
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

	public int insertDatas(QuestionBean t) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		int ret = -1;
		try {
			conn = getConn();
			String sql = "insert into t_question(questionnaireId, title, type, line, least, more, isMust)"
					+ " value(?,?,?,?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 设置值
			stat.setInt(1, t.getQuestionnaireId());
			stat.setString(2, t.getTitle());
			stat.setString(3, t.getType());
			stat.setString(4, t.getLine() == null ? "" : t.getLine());
			stat.setString(5, t.getLeast() == null ? "" : t.getLeast());
			stat.setString(6, t.getMore() == null ? "" : t.getMore());
			stat.setString(7, t.getIsMust());
			// 执行
			rowCount = stat.executeUpdate();
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				Object obj = rs.getObject(1);
				System.out.println("QuestionDaoImp:" + obj.toString());
				ret = Integer.parseInt(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		return ret;
	}

}
