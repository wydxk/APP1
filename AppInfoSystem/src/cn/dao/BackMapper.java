package cn.dao;

import org.apache.ibatis.annotations.Param;

import cn.pojo.backend_user;


public interface BackMapper {
	//根据用户编号查询用户信息
	public backend_user getUserByuserCode(@Param("userCode")String userCode);
}
