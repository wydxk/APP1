package cn.appinfodb.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
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

	private Logger log = Logger.getLogger("DevUserController.class");

	@Autowired
	DevUserService devUserService;

	@Autowired
	DataDictionaryService dataDictionaryService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	InfoService infoService;
	@Autowired
	VersionService versionService;

	/**
	 * 跳转登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/devlogin.html", method = RequestMethod.GET)
	public String info() {

		return "devlogin";

	}

	/**
	 * 登录
	 * 
	 * @param devCode
	 * @param devPassword
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/devlogin.html", method = RequestMethod.POST)
	public String dologin(@Param("devCode") String devCode,
			@Param("devPassword") String devPassword, HttpSession session) {

		DevUser devUser = devUserService.login(devCode, devPassword);
		if (devUser == null) {
			session.setAttribute("error", "用户名或密码错误！");
			return "devlogin";

		} else {
			session.setAttribute("devUserSession", devUser);
			return "/developer/main";
		}
	}

	/**
	 * 注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout.html")
	public String logout(HttpSession session) {
		session.invalidate();
		return "devlogin";
	}

	/**
	 * 跳转APP维护页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applist.html")
	public String appList(HttpSession session,
			@RequestParam(required = false) String querySoftwareName,
			@RequestParam(required = false) String queryStatus,
			@RequestParam(required = false) String queryFlatformId,
			@RequestParam(required = false) String queryCategoryLevel2,
			@RequestParam(required = false) String queryCategoryLevel1,
			@RequestParam(required = false) String queryCategoryLevel3,
			@RequestParam(required = false) String pageIndex,
			HttpServletRequest request) {
		System.out.println(querySoftwareName + "+++++++");
		if (pageIndex == null || pageIndex == "") {
			pageIndex = "1";
		}
		if (queryStatus == null || queryStatus == "") {
			queryStatus = "0";
		}
		if (queryFlatformId == null || queryFlatformId == "") {
			queryFlatformId = "0";
		}
		if (queryCategoryLevel1 == null || queryCategoryLevel1 == "") {
			queryCategoryLevel1 = "0";
		}
		if (queryCategoryLevel2 == null || queryCategoryLevel2 == "") {
			queryCategoryLevel2 = "0";
		}
		if (queryCategoryLevel3 == null || queryCategoryLevel3 == "") {
			queryCategoryLevel3 = "0";
		}

		List<DataDictionary> status = dataDictionaryService.getStatuss();
		session.setAttribute("statusList", status);

		List<DataDictionary> flat = dataDictionaryService.getflatFoList();
		session.setAttribute("flatFormList", flat);

		List<Category> categoryLevel1List = categoryService.getCategOne(Integer
				.parseInt(queryCategoryLevel1));
		session.setAttribute("categoryLevel1List", categoryLevel1List);

		/*
		 * List<Category>categoryLevel2List=categoryService.getCategTow();
		 * session.setAttribute("categoryLevel2List", categoryLevel2List);
		 * 
		 * List<Category>categoryLevel3List=categoryService.getCategThree();
		 * session.setAttribute("categoryLevel3List", categoryLevel3List);
		 */

		// 显示详情列表
		List<Info> appInfoList = infoService.getInfos(querySoftwareName,
				Integer.parseInt(queryStatus),
				Integer.parseInt(queryFlatformId),
				Integer.parseInt(queryCategoryLevel1),
				Integer.parseInt(queryCategoryLevel2),
				Integer.parseInt(queryCategoryLevel3),
				Integer.parseInt(pageIndex), Constants.PAGE_SIZE);
		session.setAttribute("appInfoList", appInfoList);

		int count = infoService.getInfoCount(querySoftwareName,
				Integer.parseInt(queryStatus),
				Integer.parseInt(queryFlatformId),
				Integer.parseInt(queryCategoryLevel1),
				Integer.parseInt(queryCategoryLevel2),
				Integer.parseInt(queryCategoryLevel3));

		int pageCount = count % Constants.PAGE_SIZE == 0 ? count
				/ Constants.PAGE_SIZE : count / Constants.PAGE_SIZE + 1;

		Map map = new HashMap();
		map.put("totalCount", count);
		map.put("currentPageNo", pageIndex);
		map.put("totalPageCount", pageCount);

		request.setAttribute("pages", map);

		request.setAttribute("querySoftwareName", querySoftwareName);
		request.setAttribute("queryStatus", queryStatus);
		request.setAttribute("queryFlatformId", queryFlatformId);
		session.setAttribute("queryCategoryLevel1", queryCategoryLevel1);
		session.setAttribute("queryCategoryLevel2", queryCategoryLevel2);
		session.setAttribute("queryCategoryLevel3", queryCategoryLevel3);

		return "developer/appinfolist";
	}

	/* 三级联动 */
	@RequestMapping(value = "/showListTow", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object showListTow(@RequestParam("pid") String pid) {

		Category category = new Category();

		category.setParentId(Integer.parseInt(pid));

		List<Category> cs = categoryService.getCategTow(Integer.parseInt(pid));

		return JSONArray.toJSONString(cs);

	}

	/**
	 * 查看信息
	 * 
	 * @param appinfoid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/appview/{appinfoid}")
	public String showInfo(@PathVariable String appinfoid,
			HttpServletRequest request) {
		if (appinfoid.trim().length() == 0) {
			return "redirect:/dev/developer/appinfolist";
		}
		Info appInfo = infoService.getInfById(Integer.parseInt(appinfoid));
		request.setAttribute("appInfo", appInfo);

		List<Version> appVersionList = versionService.getVerById(Integer
				.parseInt(appinfoid));

		request.setAttribute("appVersionList", appVersionList);

		return "developer/appinfoview";
	}

	/* 修改APP最新版本信息-历史版本列表 */
	/*
	 * @RequestMapping(value="/appversionmodify.html") public String
	 * appversionmodify(Version appVersion, int vid, int aid,HttpServletRequest
	 * request){ if(appinfoid.trim().length()==0){
	 * 
	 * return "redirect:/dev/developer/appinfolist"; }
	 * if(versionid.trim().length()==0){
	 * 
	 * return "redirect:/dev/developer/appinfolist"; } List<Version>
	 * appVersionList=versionService.getVersionByInfoIdAndVersionid(vid,aid );
	 * request.setAttribute("appVersionList", appVersionList);
	 * 
	 * System.out.println(appVersionList.size());
	 * 
	 * return "developer/appversionmodify"; }
	 */

	/* 修改最新版本信息 */
	@RequestMapping(value = "/appversionmodify", method = RequestMethod.GET)
	public String changeVersion(HttpServletRequest request, String vid,
			String aid, HttpSession session) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", vid);
		map.put("appId", aid);
		Version version = versionService.selectVersionByAid(Integer
				.parseInt(aid));

		map.put("versionNo", version.getVersionNo());
		map.put("versionSize", version.getVersionSize());
		map.put("versionInfo", version.getVersionInfo());
		map.put("downloadLink", version.getDownloadLink());
		map.put("apkLocPath", version.getApkLocPath());
		map.put("apkFileName", version.getApkFileName());
		session.setAttribute("Appver", version);
		request.setAttribute("appVersion", map);

		List<Version> versions = versionService.selectVersion(Integer
				.parseInt(aid));
		session.setAttribute("appVersionList", versions);

		return "developer/appversionmodify";

	}

	/* 修改版本 */
	@RequestMapping(value = "/addversionsave", method = RequestMethod.POST)
	public String editVersion(
			HttpServletRequest request,
			Version appversion,
			@RequestParam(value = "attach", required = false) MultipartFile attach,
			@RequestParam String appId, HttpSession session) {

		Version appver = (Version) session.getAttribute("Appver");
		DevUser devuser = (DevUser) session.getAttribute("devUserSession");

		appversion.setModifyBy(devuser.getId());
		appversion.setModifyDate(new Date());
		appversion.setId(appver.getId());
		boolean flag = versionService.changeVersion(appversion);
		if (flag) {
			return "redirect:applist.html";
		} else {
			return "redirect:appversionmodify";
		}
	}

	/* 删除APP信息 */
	@RequestMapping(value = "/delapp")
	@ResponseBody
	public Object delapp(String id) {

		Map<Object, String> map = new HashMap<Object, String>();
		Info info = infoService.getInfById(Integer.parseInt(id));
		if (info == null) {
			map.put("delResult", " notexist");
		} else {
			boolean falg = infoService.delApp(Integer.parseInt(id));
			if (falg) {
				map.put("delResult", "true");
			} else {
				map.put("delResult", "false");
			}
		}

		return JSONArray.toJSONString(map);
	}

	// 新增APP版本信息
	@RequestMapping(value = "/appversionadd", method = RequestMethod.GET)
	public String addVersion(HttpServletRequest request, String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", Integer.parseInt(id));

		List<Version> versions = versionService.selectVersion(Integer
				.parseInt(id));
		request.setAttribute("appVersionList", versions);

		return "developer/appversionadd";

	}

	// 新增APP版本信息
	@RequestMapping(value = "/appversionadd", method = RequestMethod.POST)
	public String appadd(Version version, HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="a_downloadLink",required=false) MultipartFile attach) {

		String idPicPath=null; 
		if(!attach.isEmpty()){ 			
			String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles"); 		
			String oldFileName = attach.getOriginalFilename();//原文件名 		
			String prefix = FilenameUtils.getExtension(oldFileName);//原文件名后缀 		
			int filesize = 5000000; 			 		
			if(attach.getSize()>filesize){ 			
				request.setAttribute("fileUploadError","上传文件不得超过500k"); 			
				return "developer/appversionadd"; 		
				}else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png") || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")){ 				
					String fileName = System.currentTimeMillis()+"_Personal.jpg"; 		
					File targetFile = new File(path,fileName); 			
					if(!targetFile.exists()){ 			
						targetFile.mkdirs(); 				
						} 					
										
						try {
							attach.transferTo(targetFile);
						} catch (IllegalStateException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							request.setAttribute("fileUploadError","*上传失败"); 				
							return "developer/appversionadd";
						} 
										
					idPicPath = path +File.separator+fileName; 			
					}else{ 		
						request.setAttribute("fileUploadError","*上传文件格式不正确"); 	
						return "developer/appversionadd"; 			
						} 		
			} 
		
		DevUser devuser = (DevUser)session.getAttribute("devUserSession");
		version.setCreatedBy(devuser.getId());
		version .setCreationDate(new Date());
		version.setApkFileName(idPicPath);
		version.setPublishStatus(3);
		boolean falg=versionService.addVersion(version);
		if(falg){
			Info info=new Info();
			info.setModifyBy(devuser.getId());
			info.setModifyDate(new Date());
			
			int versionId=versionService.selectId();
			info.setVersionId(versionId);
			info.setId(version.getAppId());
			boolean result=infoService.changeVersionId(info);
			if(result){
				return "redirect:applist.html";
				
			}else{
				return "developer/appversionadd";
			}
			
		}else{
		return "developer/appversionadd";
	}
	}
}
