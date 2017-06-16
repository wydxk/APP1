package cn.service;



import cn.pojo.backend_user;

public interface BackService {
	//根据用户编号查询用户信息
		public  backend_user login(String userCode,String userPassword);
}
