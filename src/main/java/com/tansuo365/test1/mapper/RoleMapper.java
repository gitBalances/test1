package com.tansuo365.test1.mapper;

import com.tansuo365.test1.bean.Role;
import com.tansuo365.test1.bean.RoleExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*角色Mapper层*/
@Mapper
public interface RoleMapper {

	int deleteByPrimaryKey(Long id);

	int insert(Role record);

	//动态插入role
	int insertSelective(Role role);

	List<Role> selectByExample(RoleExample example);

	//动态获取Role表数据
	List<Role> selectRoleSelective(Role role);

	Role selectByPrimaryKey(Long id);

	Role selectByName(String name);

	//动态更新role
	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);
}