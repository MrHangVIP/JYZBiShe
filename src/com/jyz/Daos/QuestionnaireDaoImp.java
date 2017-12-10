package com.jyz.Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.jyz.Daos.base.BaseDBFactor;
import com.jyz.beans.QuestionnaireBean;
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
            String sql="select a.headurl,a.nickname,b.* from t_user as a "
            		+ "inner join t_questionnaire as b "
            		+ "where a.userid=b.userid "
            		+ "order by questionnaireId desc";
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
			String sql = "insert into t_questionnaire(userId, title,introduce,thanks,createtime,createtimestmp,finishtime,finishtimestmp)"
					+ " value(?,?,?,?,?,?,?,?)";
			stat = conn.prepareStatement(sql);
			// 设置值
			stat.setInt(1, t.getUserId());
			stat.setString(2, t.getTitle());
			stat.setString(3, t.getIntroduce());
			stat.setString(4, t.getThanks());
			stat.setString(5, DateUtil.getCurrentDate());
			stat.setLong(6, System.currentTimeMillis());
			stat.setString(7,t.getFinishTime());
			stat.setLong(8, t.getFinishTimeStmp());
			// 执行
			rowCount = stat.executeUpdate();
			if(rowCount>0){
				ResultSet rs = stat.getGeneratedKeys();
				if (rs.next()) {
					Object obj = rs.getObject(1);
					System.out.println("QuestionnaireDaoImp:"+obj.toString());
					ret=Integer.parseInt(obj.toString());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConn(stat, conn);
		}
		return ret;
	}
	
	/**
	 * 获取用户发布的问卷
	 * @param userId
	 * @return
	 */
	public List<QuestionnaireBean> getSomeOneDataList(int userId) {
		Connection conn=null;
		List<QuestionnaireBean> questionnaireBeans=null;
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			String sql="select a.headurl,a.nickname,b.* from t_user as a "
            		+ "inner join t_questionnaire as b "
            		+ "where a.userid=b.userid and b.userId= ? "
            		+ "order by questionnaireId desc";
			questionnaireBeans=(List<QuestionnaireBean>)qr.query(conn,sql,new BeanListHandler<QuestionnaireBean>(QuestionnaireBean.class),userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn);
		}
		
		return questionnaireBeans;
	}
	
	/**
	 * 获取用户回答的问卷
	 * @param userId
	 * @return
	 */
	public List<QuestionnaireBean> getAnswerList(int userId) {
		Connection conn=null;
		List<QuestionnaireBean> questionnaireBeans=null;
		try {
			conn=getConn();
			QueryRunner qr=new QueryRunner();
			String sql= " select distinct (a.questionnaireid),a.*,c.nickname,c.headurl "
					+ "from t_questionnaire as a "
					+ "inner join t_answer as b "
					+ "on a.questionnaireid=b.questionnaireid "
					+ "inner join t_user as c "
					+ "on a.userid=c.userid "
					+ "where b.userid= ? "
					+ "order by a.questionnaireid desc";
			questionnaireBeans=(List<QuestionnaireBean>)qr.query(conn,sql,new BeanListHandler<QuestionnaireBean>(QuestionnaireBean.class),userId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn); 
		}
		return questionnaireBeans;
	}
	
	/**
	 * 获取问卷的回答数量
	 * @param qusetionnaireId
	 * @return
	 */
	public int getAnswerNum(int qusetionnaireId) {
		Connection conn=null;
		PreparedStatement stat = null;
		int count=0;
		try {
			conn=getConn();
			String sql="select count( distinct userId) as num from t_answer where questionnaireId= "+ qusetionnaireId ;
			stat = conn.prepareStatement(sql);
			ResultSet rs=stat.executeQuery();
			count=rs.getInt("num");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConn(null, conn);
		}
		return count;
	}

}
