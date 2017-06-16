package cn.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.pojo.backend_user;

import cn.service.BackService;

@RequestMapping("/back")
@Controller
public class BackinfoController {
	
	
	@Autowired 
	BackService backService;
	//登陆
		@RequestMapping(value="/backlogin.html",method=RequestMethod.GET)
		public String login(){
			return "backendlogin";
		}
		//登陆
		@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
		public String login(@Param("userCode")String userCode, @Param("userPassword")String userPassword,HttpSession session){
			

			backend_user user= backService.login(userCode, userPassword);
			if(user == null){
				session.setAttribute("error", "用户名或密码错误！");
				return "backendlogin";
			}else{
				//登录成功 			
				session.setAttribute("userSession", user); 
				return "backend/main";
			}
		}
}
