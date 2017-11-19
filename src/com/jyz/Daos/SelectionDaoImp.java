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
import com.jyz.beans.SelectionBean;

public class SelectionDaoImp extends BaseDBFactor<SelectionBean>{

	
	
	@Override
	public boolean insertData(SelectionBean t) {
		Connection conn = null;
		PreparedStatement stat = null;
		int rowCount = 0;
		try {
			conn = getConn();
			String sql = "insert into t_selection(questionId, title,isSelect,orders)"
					+ " value(?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 设置值
			stat.setInt(1, t.getQuestionId());
			stat.setString(2, t.getTitle());
			stat.setString(3, t.getIsSelect());
			stat.setString(4, t.getOrder());
			// 执行
			rowCount = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		if(rowCount>0){
			return true;
		}
		return false;
	}

	@Override
	public List<SelectionBean> getDataList(Object... obj) {
		int questionid=(int)obj[0];
		Connection conn=null;
		List<SelectionBean> questionBeans=null;
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			String sql="select * from t_selection where questionid = ?";
			questionBeans=(List<SelectionBean>)qr.query(conn,sql,new BeanListHandler<SelectionBean>(SelectionBean.class),questionid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn);
		}
		return questionBeans;
	}

	@Override
	public SelectionBean getData(Object... obj) {
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
