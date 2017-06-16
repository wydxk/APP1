package cn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.UserMapper;

import cn.pojo.dev_user;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserMapper userMapper;

	@Override
	public dev_user login(String devCode, String devPassword) {
		
		dev_user user = null;
		 user = userMapper.getUserBydevCode(devCode);
		 if(user != null){
			 //判断密码是否正确
			 if(!user.getDevPassword().equals(devPassword)){
				 user = null;
			 }
			 
		 }
		
		return user;
	}

}
