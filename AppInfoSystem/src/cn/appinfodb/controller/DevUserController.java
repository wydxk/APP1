package cn.appinfodb.controller;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONArray;
import cn.appinfodb.pojo.Category;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.pojo.Info;
import cn.appinfodb.pojo.Version;
import cn.appinfodb.service.Category.CategoryService;
import cn.appinfodb.service.DataDictionary.DataDictionaryService;
import cn.appinfodb.service.Info.InfoService;
import cn.appinfodb.service.Version.VersionService;
import cn.appinfodb.service.devUser.DevUserService;
import cn.appinfodb.tools.Constants;
@RequestMapping("/dev")
@Controller
public class DevUserController {	
	
	private Logger logger=Logger.getLogger(DevUserController.class);
	@Autowired
	DevUserService devUserService;
	@Autowired
	InfoService infoService;
	@Autowired
	DataDictionaryService dataDictionaryService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	VersionService versionService;
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
			@RequestParam(required=false)  String queryCategoryLevel3,
			@RequestParam(required=false) String pageIndex,
			HttpServletRequest request
			){
		if(pageIndex==null||pageIndex==""){
			pageIndex="1";
		}
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
		
		List<Category>categoryLevel1List= categoryService.getCategOne(Integer.parseInt(queryCategoryLevel1));
		session.setAttribute("categoryLevel1List", categoryLevel1List);
		
		//显示详情列表
		List<Info> appInfoList=infoService.getInfos(querySoftwareName, 
				Integer.parseInt(queryStatus), 
				Integer.parseInt(queryFlatformId), 
				Integer.parseInt(queryCategoryLevel1), 
				Integer.parseInt(queryCategoryLevel2), 
				Integer.parseInt(queryCategoryLevel3),
				Integer.parseInt(pageIndex), Constants.PAGE_SIZE );
		session.setAttribute("appInfoList", appInfoList);
		
		int count=infoService.getInfoCount(querySoftwareName, Integer.parseInt(queryStatus),Integer.parseInt(queryFlatformId), Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3));
		
		int pageCount=count%Constants.PAGE_SIZE==0?count/Constants.PAGE_SIZE:count/Constants.PAGE_SIZE+1;
		
		Map map=new HashMap();
		map.put("totalCount", count);
		map.put("currentPageNo",pageIndex );
		map.put("totalPageCount", pageCount);
		
		request.setAttribute("pages",map );
		
		request.setAttribute("querySoftwareName",querySoftwareName);
		request.setAttribute("queryStatus",queryStatus);
		request.setAttribute("queryFlatformId",queryFlatformId);
		session.setAttribute("queryCategoryLevel1",queryCategoryLevel1);
		session.setAttribute("queryCategoryLevel2",queryCategoryLevel2);
		session.setAttribute("queryCategoryLevel3",queryCategoryLevel3);
		
		return "developer/appinfolist";
	}
	
	/*三级联动*/
	@RequestMapping(value="/showListTow",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public Object showListTow(@RequestParam("pid") String pid){
		
		Category category=new Category();
		
		category.setParentId(Integer.parseInt(pid));
		
		List<Category> cs=categoryService.getCategTow(Integer.parseInt(pid));
		
		return JSONArray.toJSONString(cs);
		
	}
	
	/**
	 * 查看信息
	 * @param appinfoid
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/appview/{appinfoid}")
	public String showInfo(@PathVariable String appinfoid,HttpServletRequest request){
		if(appinfoid.trim().length()==0){
			return "redirect:/dev/developer/appinfolist";
		}
		Info appInfo=infoService.getInfById(Integer.parseInt(appinfoid));
		request.setAttribute("appInfo", appInfo);
		
		List<Version> appVersionList=versionService.getVerById(Integer.parseInt(appinfoid));
		
		request.setAttribute("appVersionList", appVersionList);
		
		return "developer/appinfoview";
	}
	
	/*修改APP最新版本信息，历史版本列表*/
	@RequestMapping(value="/appversionmodify.html")
	public String appversionmodify(int vid,
			int aid,HttpServletRequest request){
		/*if(appinfoid.trim().length()==0){
			
			return "redirect:/dev/developer/appinfolist";
		}
		if(versionid.trim().length()==0){
			
			return "redirect:/dev/developer/appinfolist";
		}*/
		List<Version> appVersionList=versionService.getVersionByInfoIdAndVersionid(vid,aid );
		request.setAttribute("appVersionList", appVersionList);
		
		System.out.println(appVersionList.size());
		
		return "developer/appversionmodify";
	}	
	
@RequestMapping(value="/addDev",method=RequestMethod.GET)
	public String addDevUser(){
		return "developer/appinfoadd";
	}
@RequestMapping(value="/doaddDev",method=RequestMethod.POST)
	public String addDevUser(Info info,HttpSession session,HttpServletRequest request,
			 @RequestParam(value ="a_logoPicPath", required = false) MultipartFile attach){
		String logPicPath = null;
		//判断文件是否为空
		if(!attach.isEmpty()){
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");		
			String oldFileName = attach.getOriginalFilename();//原文件名		
			String prefix=FilenameUtils.getExtension(oldFileName);//原文件后缀    	       
			int filesize = 500000;
			
	        if(attach.getSize() >  filesize){//上传大小不得超过 500k
          	request.setAttribute("uploadFileError", " * 上传大小不得超过 500k");
	        	return "redirect:dev/addDev";
          }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") 
          		|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式不正确
          	String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_Personal.jpg";  
              logger.debug("new fileName======== " + attach.getName());
              File targetFile = new File(path, fileName);  
              if(!targetFile.exists()){  
                  targetFile.mkdirs();  
              }  
              //保存  
              try {  
              	attach.transferTo(targetFile);  
              } catch (Exception e) {  
                  e.printStackTrace();  
                  request.setAttribute("uploadFileError", " * 上传失败！");
              		return "redirect:dev/addDev";
              }  
              logPicPath = path+File.separator+fileName;
          }else{
          	request.setAttribute("uploadFileError", " * 上传图片格式不正确");
          	return "redirect:dev/addDev";
          }
		}
		
		/*info.setCreatedBy(session.getAttribute(Constants.BACKENDUSER_SESSION.))*/
		info.setCreationDate(new Date());
		info.setLogoPicPath(logPicPath);
		
		if (infoService.addInfo(info)) {
			return "developer/appinfolist";
		}
		return "developer/appinfoadd";
	}
	

	
@RequestMapping(value="/flatformName",method=RequestMethod.GET)
@ResponseBody
public Object showflatformName(){
	List<DataDictionary> dataDictionary=dataDictionaryService.getflatFoList();
	return JSONArray.toJSONString(dataDictionary);
}



@RequestMapping(value="/categorylevellist",method=RequestMethod.GET)
@ResponseBody
public Object showCategorylevellist(int pid){
	System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
	List<Category> cates=categoryService.getCategTow(pid);
	System.out.println(pid);
	return JSONArray.toJSONString(cates);
}

@RequestMapping(value="/apkexist",method=RequestMethod.GET)
@ResponseBody
public Object apkexist(String APK){
	Map<String, Object> map=new HashMap<String,Object>();
	if (APK.trim().length()==0) {
		map.put("APK", "empty");
	}else{
		boolean flag=infoService.selectByAPK(APK);
		if (flag) {
			map.put("APK", "exist");
		}else{
			map.put("APK", "noexist");
		}
	}
	return JSONArray.toJSONString(map);
}

/*//修改APP基础信息
@RequestMapping(value="/appinfomodify")
public String updateInfo( int id,HttpServletRequest request){
	Info info=infoService.getInfById(id);
	request.setAttribute("appInfo", info);
	return "developer/appinfomodify";	
}*/

}
