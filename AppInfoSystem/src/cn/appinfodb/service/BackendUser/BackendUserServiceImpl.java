package cn.appinfodb.service.BackendUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.BackendUser.BackendUserMapper;
import cn.appinfodb.pojo.BackendUser;

@Service("backendUserService")
public class BackendUserServiceImpl implements BackendUserService {
	@Autowired
	BackendUserMapper backendUserMapper;
	@Override
	public BackendUser fountBackendUserBy(String userCode, String userPassword) {
		BackendUser backendUser=null;
		 backendUser=backendUserMapper.selectBackendUserBy(userCode);
		if (backendUser!=null) {
			if (!backendUser.getUserPassword().equals(userPassword)) {
				backendUser=null;
			}
		}
		return backendUser;
		
	}
}
