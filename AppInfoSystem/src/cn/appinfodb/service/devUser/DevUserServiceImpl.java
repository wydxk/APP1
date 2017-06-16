package cn.appinfodb.service.devUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.devUser.DevUserMapper;
import cn.appinfodb.pojo.DevUser;

@Service("devUserService")
public class DevUserServiceImpl implements DevUserService {
	@Autowired
	DevUserMapper devUserMapper;
	@Override
	public DevUser login(String devCode, String devPassword) {
		DevUser  devUser=null;
		devUser = devUserMapper.getDevUserBuDevCode(devCode);
		if (devUser!=null) {
			if (devUser.getDevPassword()==devPassword) {
				devUser=null;
			}
		}
		return devUser;
		
	}

}
