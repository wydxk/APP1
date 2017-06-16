package cn.dao;

import org.apache.ibatis.annotations.Param;


import cn.pojo.dev_user;

public interface UserMapper {
	//根据用户编号查询用户信息
			public dev_user getUserBydevCode(@Param("devCode")String devCode);
}
