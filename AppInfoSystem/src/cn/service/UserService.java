package cn.service;

import cn.pojo.dev_user;

public interface UserService {
	//登陆
	public dev_user login(String devCode , String devPassword);
}
