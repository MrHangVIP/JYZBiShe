package com.jyz.Daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jyz.Daos.base.BaseDBFactor;
import com.jyz.beans.QuestionnaireBean;
import com.jyz.beans.UserBean;
import com.jyz.utils.DateUtil;

public class QuestionnaireDaoImp extends BaseDBFactor<QuestionnaireBean> {

	@Override
	public boolean insertData(QuestionnaireBean t) {
		return false;
	}

	@Override
	public List<QuestionnaireBean> getDataList(Object... obj) {
		Connection conn=null;
		List<QuestionnaireBean> questionnaireBeans=null;
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			String sql="select * from t_questionnaire";
			questionnaireBeans=(List<QuestionnaireBean>)qr.query(conn,sql,new BeanListHandler<QuestionnaireBean>(QuestionnaireBean.class));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn);
		}
		
		return questionnaireBeans;
	}

	@Override
	public QuestionnaireBean getData(Object... obj) {
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

	public int insertDatas(QuestionnaireBean t) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		int ret=-1;
		try {
			conn = getConn();
			String sql = "insert into t_questionnaire(userId, title,introduce,thanks,createtime,createtimestmp,nickname)"
					+ " value(?,?,?,?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 设置值
			stat.setInt(1, t.getUserId());
			stat.setString(2, t.getTitle());
			stat.setString(3, t.getIntroduce());
			stat.setString(4, t.getThanks());
			stat.setString(5, DateUtil.getCurrentDate());
			stat.setLong(6, System.currentTimeMillis());
			stat.setString(7, t.getNickName());
			// 执行
			rowCount = stat.executeUpdate();
			ResultSet rs = stat.getGeneratedKeys();
			if (rs.next()) {
				Object obj = rs.getObject(1);
				System.out.println("QuestionnaireDaoImp:"+obj.toString());
				ret=Integer.parseInt(obj.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		return ret;
	}

}
