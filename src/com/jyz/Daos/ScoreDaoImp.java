package com.jyz.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.jyz.Daos.base.BaseDBFactor;
import com.jyz.beans.ScoreBean;
import com.jyz.beans.ScoreChangeBean;
import com.jyz.utils.DateUtil;

public class ScoreDaoImp extends BaseDBFactor<ScoreBean>{
	
	
	@Override
	public boolean insertData(ScoreBean t) {
		Connection conn=null;
		PreparedStatement stat=null;
		 int rowCount=0;
		try {
			conn=getConn();
			String sql="insert into t_score(userId, score) value(?,?)";
			stat=conn.prepareStatement(sql);
			//设置值
			stat.setInt(1, t.getUserId());
			stat.setInt(2, t.getScore());
			//执行
			rowCount=stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(stat, conn);
		}
		if(rowCount>0){
			//插入成功则更新 记录表
			ScoreChangeDaoImp scoreChangeDaoImp=new ScoreChangeDaoImp();
			ScoreChangeBean scoreChangeItem=new ScoreChangeBean();
			scoreChangeItem.setUserId(t.getUserId());
			scoreChangeItem.setCreatetime(DateUtil.getCurrentDate());
			scoreChangeItem.setAction("用户注册赠送10积分");
			scoreChangeItem.setScore_change(t.getScore());
			scoreChangeItem.setQuestionnaireId(0);
			if(scoreChangeDaoImp.insertData(scoreChangeItem)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public List<ScoreBean> getDataList(Object... obj) {
		return null;
	}

	@Override
	public boolean updateData(Object... obj) {//大概是三个参数 增加还是减少，用户id，问卷id
		boolean isAdd=(boolean)obj[0];
		int userId=(int)obj[1];
		int questionnaireId=(int)obj[2];
		Connection conn=null;
		PreparedStatement stat=null;
		 int rowCount=0;
		try {
			conn=getConn();
			int score=getData(userId).getScore();
			if(isAdd){
				score=score+5;
			}else{
				score=score-10;
			}
			String sql="update t_score set score= ? where userId= ?";
			stat=conn.prepareStatement(sql);
			//设置值
			stat.setInt(1,score);
			stat.setInt(2,userId);

			//执行
			rowCount=stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(stat, conn);
		}
		if(rowCount>0){
			//更新积分的时候插入变更记录
			ScoreChangeDaoImp scoreChangeDaoImp=new ScoreChangeDaoImp();
			ScoreChangeBean scoreChangeItem=new ScoreChangeBean();
			scoreChangeItem.setUserId(userId);
			scoreChangeItem.setCreatetime(DateUtil.getCurrentDate());
			if(isAdd){
				scoreChangeItem.setScore_change(+5);
				scoreChangeItem.setAction("回答问卷增加5积分");
			}else{
				scoreChangeItem.setScore_change(-10);
				scoreChangeItem.setAction("发表问卷扣除10积分");
			}
			scoreChangeItem.setQuestionnaireId(questionnaireId);
			if(	scoreChangeDaoImp.insertData(scoreChangeItem)){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean deleteData(int id) {
		return false;
	}

	@Override
	public ScoreBean getData(Object... obj) {
		Connection conn=null;
		ScoreBean scoreBean=null;
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			String sql="select * from t_score where userId = ? ";
			scoreBean=(ScoreBean)qr.query(conn,sql,new BeanHandler<ScoreBean>(ScoreBean.class),obj[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn);
		}
		return scoreBean;
	}

}
