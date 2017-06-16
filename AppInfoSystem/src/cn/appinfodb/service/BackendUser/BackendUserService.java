package cn.appinfodb.service.BackendUser;

import cn.appinfodb.pojo.BackendUser;

public interface BackendUserService {
/**
 * 用户登录
 * @param userCode
 * @return
 */
	public BackendUser fountBackendUserBy(String userCode,String userPassword);
	
}
