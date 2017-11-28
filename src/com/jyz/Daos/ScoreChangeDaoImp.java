package com.jyz.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jyz.Daos.base.BaseDBFactor;
import com.jyz.beans.ScoreChangeBean;
import com.jyz.beans.SelectionBean;
import com.jyz.utils.DateUtil;

public class ScoreChangeDaoImp extends BaseDBFactor<ScoreChangeBean>{

	@Override
	public boolean insertData(ScoreChangeBean t) {
		Connection conn=null;
		PreparedStatement stat=null;
		 int rowCount=0;
		try {
			conn=getConn();
			String sql="insert into t_score_record(userId, score_change, createtime, action, questionnaireId) "
					+ "value(?,?,?,?,?)";
			stat=conn.prepareStatement(sql);
			//设置值
			stat.setInt(1, t.getUserId());
			stat.setInt(2, t.getScore_change());
			stat.setString(3,DateUtil.getCurrentDate());
			stat.setString(4, t.getAction());
			stat.setInt(5, t.getQuestionnaireId());
			//执行
			rowCount=stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(stat, conn);
		}
		if(rowCount>0){
			return true;
		}
		return false;
	}

	@Override
	public List<ScoreChangeBean> getDataList(Object... obj) {
		int userid=Integer.parseInt(obj[0].toString());
		Connection conn=null;
		List<ScoreChangeBean> scorechangeList=null;
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			String sql="select * from t_score_record where userId = ? order by id desc";
			scorechangeList=(List<ScoreChangeBean>)qr.query(conn,sql,new BeanListHandler<ScoreChangeBean>(ScoreChangeBean.class),userid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn);
		}
		return scorechangeList;
	}

	@Override
	public boolean updateData(Object... obj) {
		return false;
	}

	@Override
	public boolean deleteData(int id) {
		return false;
	}

	@Override
	public ScoreChangeBean getData(Object... obj) {
		return null;
	}

}
