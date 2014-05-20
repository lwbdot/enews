package com.lwb.services.implementts;

import java.util.List;

import com.lwb.services.interfaces.UserInfoServiceInter;
import com.lwb.ssh.basics.BasicService;
import com.lwb.ssh.domain.UserInfo;

public class UserInfoService extends BasicService implements UserInfoServiceInter {
	private String tb_name = "UserInfo";
	public List getItems(int currentPage,int pageSize){
		String hql = "from "+tb_name;
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}
	
	public int getItemsCount(){
		String hql = "select count(*) from "+tb_name;
		Object count = this.uniqueQuery(hql, null);
		return new Integer(count.toString());
	}
	
	public List getItems(){
		String hql = "from "+tb_name+" order by id";
		return executeQuery(hql, null);
	}
	
	public UserInfo checkItem(UserInfo e) {
		String hql=" from "+tb_name+" where name=? and password=?"; 
		Object []paras = {e.getUserName(),e.getUserPass()};
		List<UserInfo> list = this.executeQuery(hql, paras);
		if((null != list)&&(list.size() == 1)){
			return list.get(0);
		}
		return null;
	}

	public UserInfo getUserInfoByName(String userName) {
		String hql=" from "+tb_name+" where userName='"+userName+"'"; 
		List<UserInfo> list = this.executeQuery(hql, null);
		if((null != list)&&(list.size() == 1)){
			return list.get(0);
		}
		return null;
	}
}
