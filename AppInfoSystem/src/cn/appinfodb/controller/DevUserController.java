package cn.appinfodb.controller;

import java.io.Console;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.appinfodb.pojo.Category;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.pojo.Info;
import cn.appinfodb.service.Category.CategoryService;
import cn.appinfodb.service.DataDictionary.DataDictionaryService;
import cn.appinfodb.service.Info.InfoService;
import cn.appinfodb.service.devUser.DevUserService;
import cn.appinfodb.tools.Constants;

@RequestMapping("/dev")
@Controller
public class DevUserController {

	private Logger log = Logger.getLogger("DevUserController.class");
	
	@Autowired
	DevUserService devUserService;
	
	@Autowired
	DataDictionaryService dataDictionaryService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	InfoService infoService;
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value="/devlogin.html",method=RequestMethod.GET)
	public String info(){
		
		return "devlogin";
		
	}
	/**
	 * 登录
	 * @param devCode
	 * @param devPassword
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/devlogin.html",method=RequestMethod.POST)
	public String dologin(@Param("devCode")String devCode,@Param("devPassword")String devPassword,HttpSession session){
		
		DevUser devUser = devUserService.login(devCode, devPassword);
		if(devUser==null){
			session.setAttribute("error", "用户名或密码错误！");
			return "devlogin";
			
		}else{
			session.setAttribute("devUserSession", devUser);
			return "/developer/main";
		}
	}
	
	
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session){
		session.invalidate();
		return "devlogin";
	}
	/**
	 * 跳转APP维护页面
	 * @return
	 */
	@RequestMapping(value="/applist.html")
	public String appList(HttpSession session,
			@RequestParam(required=false)  String querySoftwareName,
			@RequestParam(required=false)  String queryStatus,
			@RequestParam(required=false)  String queryFlatformId,
			@RequestParam(required=false)  String queryCategoryLevel2,
			@RequestParam(required=false)  String queryCategoryLevel1,
			@RequestParam(required=false)  String queryCategoryLevel3
			
			){
		
		if(queryStatus==null ||queryStatus==""){
			queryStatus="0";
		}
		if(queryFlatformId==null || queryFlatformId==""){
			queryFlatformId="0";
		}
		if(queryCategoryLevel1==null ||queryCategoryLevel1==""){
			queryCategoryLevel1="0";
		}
		if(queryCategoryLevel2==null ||queryCategoryLevel2==""){
			queryCategoryLevel2="0";
		}
		if(queryCategoryLevel3==null || queryCategoryLevel3==""){
			queryCategoryLevel3="0";
		}
		
		
		
		
		List<DataDictionary> status= dataDictionaryService.getStatuss();
		session.setAttribute("statusList", status);
		
		List<DataDictionary> flat=dataDictionaryService.getflatFoList();
		session.setAttribute("flatFormList", flat);
		
		List<Category>categoryLevel1List= categoryService.getCategOne();
		session.setAttribute("categoryLevel1List", categoryLevel1List);
		
		/*List<Category>categoryLevel2List=categoryService.getCategTow();
		session.setAttribute("categoryLevel2List", categoryLevel2List);
		
		List<Category>categoryLevel3List=categoryService.getCategThree();
		session.setAttribute("categoryLevel3List", categoryLevel3List);*/
		
		
		//显示详情
		List<Info> appInfoList=infoService.getInfos(querySoftwareName, 
				Integer.parseInt(queryStatus), 
				Integer.parseInt(queryFlatformId), 
				Integer.parseInt(queryCategoryLevel1), 
				Integer.parseInt(queryCategoryLevel2), 
				Integer.parseInt(queryCategoryLevel3) );
		session.setAttribute("appInfoList", appInfoList);
		
		
		return "developer/appinfolist";
	}
	
/*	querySoftwareName,Integer.parseInt(queryFlatformId) ,Integer.parseInt(queryStatus) , 
	Integer.parseInt(queryCategoryLevel1),
	Integer.parseInt(queryCategoryLevel2) ,Integer.parseInt(queryCategoryLevel3)*/
	
	@RequestMapping(value="showListTow")
	@ResponseBody
	public Object showListTow(@RequestParam("pid") String pid){
		
		List<Category> cs=categoryService.getCategTow(Integer.parseInt(pid));
		
		return JSON.toJSONString(cs);
		
		
	}
	
	
	
	
	
}
