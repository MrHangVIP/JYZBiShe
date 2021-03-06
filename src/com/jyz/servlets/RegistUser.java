/**
 * 
 */
package com.jyz.servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyz.Daos.ScoreDaoImp;
import com.jyz.Daos.UserDaoImp;
import com.jyz.beans.ScoreBean;
import com.jyz.beans.UserBean;
import com.jyz.servlets.base.BaseServletFactory;

/**
 * @author moram
 *
 */
public class RegistUser extends BaseServletFactory{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected Map<String, String> dataModel(HttpServletRequest request, HttpServletResponse response) {
		String userPhone=request.getParameter("userPhone");
		String userPass=request.getParameter("userPass");
		UserBean user=new UserBean();
		user.setUserPhone(userPhone);
		user.setUserPass(userPass);
		UserDaoImp usermodel=new UserDaoImp();
		boolean isExist=usermodel.userPhoneChecked(userPhone);
		Map<String, String> map = new HashMap<String, String>();
		if(isExist){
			map.put("result", "fail");
			map.put("data", "exist");
		}else{
			boolean result=usermodel.insertData(user);
			if(result){
                UserBean userbean=usermodel.getUserInfo(userPhone);
                //注冊成功 添加积分
                ScoreDaoImp scoreDaoImp=new ScoreDaoImp();
                ScoreBean bean=new ScoreBean();
                bean.setScore(10);
                bean.setUserId(userbean.getUserId());
                scoreDaoImp.insertData(bean);
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}
		}
		return map;
	}
	
	
	

}
