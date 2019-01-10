package com.tansuo365.test1.service;


import com.tansuo365.test1.bean.Role;

/*角色权限服务接口*/
public interface RolePermissionService {
	public void setPermissions(Role role, long[] permissionIds);

	public void deleteByRole(long roleId);

	public void deleteByPermission(long permissionId);
}