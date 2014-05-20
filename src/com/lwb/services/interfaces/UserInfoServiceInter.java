package com.lwb.services.interfaces;

import java.util.List;

import com.lwb.ssh.basics.BasicServiceInter;
import com.lwb.ssh.domain.UserInfo;

public interface UserInfoServiceInter extends BasicServiceInter {
	public List getItems();
	public List getItems(int currentPage,int pageSize);
	public int getItemsCount();
	public UserInfo getUserInfoByName(String userName);

	/**
	 *  检查UserInfo是否存在于数据库，如果存在，
	 *  则返回完整的UserInfo，否则返回null
	 * @param e
	 * @return
	 */
	public UserInfo checkItem(UserInfo e);
}
