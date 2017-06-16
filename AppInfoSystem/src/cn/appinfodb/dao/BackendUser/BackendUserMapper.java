package cn.appinfodb.dao.BackendUser;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.BackendUser;

public interface BackendUserMapper {
		/**
		 * 根据用户编码查找用户信息
		 * @param userCode
		 * @return
		 */
	public BackendUser selectBackendUserBy(@Param("userCode")String userCode);
}
