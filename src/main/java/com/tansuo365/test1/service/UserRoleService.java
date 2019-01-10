package com.tansuo365.test1.service;


import com.tansuo365.test1.bean.User;

/*用户角色服务接口*/
public interface UserRoleService {

	public void setRoles(User user, long[] roleIds);

	public void deleteByUser(long userId);

	public void deleteByRole(long roleId);

}