package cn.appinfodb.service.devUser;

import cn.appinfodb.pojo.DevUser;



public interface DevUserService {

	public DevUser login(String devCode,String devPassword);
	
}
