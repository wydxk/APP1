package cn.appinfodb.dao.devUser;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.DevUser;

public interface DevUserMapper {

	/**
	 * 根据devCode查询用户信息
	 * @param devCode
	 * @return
	 */
	public DevUser getDevUserBuDevCode(@Param("devCode")String devCode);
	
	
	
	
	
	
}
