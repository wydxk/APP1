package cn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;



import cn.pojo.app_category;
import cn.pojo.app_info;
import cn.pojo.data_dictionary;
import cn.pojo.dev_user;
import cn.service.App_infoService;
import cn.service.CateGoryService;
import cn.service.Data_dictionaryService;
import cn.service.UserService;


@RequestMapping("/user")
@Controller
public class controller {
	@Autowired 
	UserService userService;
	@Autowired 
	App_infoService app_infoService;
	@Autowired
	Data_dictionaryService data_dictionaryService;
	@Autowired
	CateGoryService cateGoryService;
	
	//登陆
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public String login(){
		return "devlogin";
	}
	//登陆
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String login(@Param("devCode")String devCode, @Param("devPassword")String devPassword,HttpSession session){
		

		dev_user user= userService.login(devCode, devPassword);
		if(user == null){
			session.setAttribute("error", "用户名或密码错误！");
			return "devlogin";
		}else{
			//登录成功 			
			session.setAttribute("devUserSession", user); 
			return "developer/main";
		}
	}
		
	//加载页面
	/*@RequestMapping(value="/applist.html",method=RequestMethod.GET)
	public String applist(HttpSession session){
		List<data_dictionary> dictionary =data_dictionaryService.getdictionary();
		session.setAttribute("statusList", dictionary);
		List<data_dictionary> dictionarys =data_dictionaryService.getdata_dictionarys();
		session.setAttribute("flatFormList", dictionary);
		return "developer/appinfolist";
	}*/
	
	@RequestMapping(value="/appinfolist.html")
	public String applist(HttpServletRequest request,				
			@RequestParam(required=false)String querySoftwareName,
			@RequestParam(required=false)String queryStatus,
			@RequestParam(required=false)String queryFlatformId,
			@RequestParam(required=false)String pageIndex,
			@RequestParam(required=false)String queryCategoryLevel1,
			@RequestParam(required=false)String queryCategoryLevel2,
			@RequestParam(required=false)String queryCategoryLevel3){
	
		if(queryCategoryLevel1==null || queryCategoryLevel1.equals("")){
			queryCategoryLevel1="0";
		}
		if(queryCategoryLevel2==null || queryCategoryLevel2.equals("")){
			queryCategoryLevel2="0";
		}
		if(queryCategoryLevel3==null || queryCategoryLevel3.equals("")){
			queryCategoryLevel3="0";
		}
		if(pageIndex==null || pageIndex.equals("")){
			pageIndex="1";
		}
		List<data_dictionary> dictionary =data_dictionaryService.getdictionary();
		request.setAttribute("flatFormList", dictionary);
		List<data_dictionary> dictionarys =data_dictionaryService.getdata_dictionarys();
		request.setAttribute("statusList", dictionarys);
		if(queryStatus==null || queryStatus.equals("")){
			queryStatus="0";
		}
	
		if(queryFlatformId==null || queryFlatformId.equals("")){
			queryFlatformId="0";
		}
		List<app_category> category1=cateGoryService.getcategory1(Integer.parseInt(queryCategoryLevel1));
		request.setAttribute("categoryLevel1List", category1);
		List<app_category> category2=cateGoryService.getCategory2(Integer.parseInt(queryCategoryLevel2));
		request.setAttribute("categoryLevel2List", category2);
		List<app_category> category3=cateGoryService.getCategory3(Integer.parseInt(queryCategoryLevel3));
		request.setAttribute("queryCategoryLevel3", category3);
		
		List<app_info> applist =app_infoService.getAllApp(querySoftwareName,Integer.parseInt(queryStatus),Integer.parseInt(queryFlatformId),Integer.parseInt(pageIndex), Constants.PAGE_SIZE);
		request.setAttribute("appInfoList", applist);
		
		request.setAttribute("querySoftwareName", querySoftwareName);
		request.setAttribute("queryStatus", queryStatus);
		request.setAttribute("queryFlatformId", queryFlatformId);
		
		
		
		int count=app_infoService.getCountByConcent(querySoftwareName,Integer.parseInt(queryStatus),Integer.parseInt(queryFlatformId));
		int pageCount=count%Constants.PAGE_SIZE==0?count/Constants.PAGE_SIZE:count/Constants.PAGE_SIZE +1;
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("totalCount", count);
		map.put("currentPageNo", pageIndex);
		map.put("totalPageCount", pageCount);
		request.setAttribute("pages", map);
		return "developer/appinfolist";
	}
	
	

	@RequestMapping(value="/cateList",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Object categorylevellist(@RequestParam  String pid){
		app_category cateapp = new app_category();
		cateapp.setParentId(Integer.parseInt(pid));	
		List<app_category>  cate  = cateGoryService.getCategory2(Integer.parseInt(pid));
		return JSONArray.toJSONString(cate);
	}

	
}
