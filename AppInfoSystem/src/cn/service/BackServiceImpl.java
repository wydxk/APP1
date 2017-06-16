package cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.BackMapper;

import cn.pojo.backend_user;

@Service("backService")
public class BackServiceImpl implements BackService{

	@Autowired
	public BackMapper backMapper;

	@Override
	public backend_user login(String userCode, String userPassword) {
		
		backend_user user = null;
		 user = backMapper.getUserByuserCode(userCode);
		 if(user != null){
			 //判断密码是否正确
			 if(!user.getUserPassword().equals(userPassword)){
				 user = null;
			 }
			 
		 }
		
		return user;

}

}
