package cn.appinfodb.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import cn.appinfodb.pojo.Category;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.pojo.Info;
import cn.appinfodb.pojo.Version;
import cn.appinfodb.service.Category.CategoryService;
import cn.appinfodb.service.DataDictionary.DataDictionaryService;
import cn.appinfodb.service.Info.InfoService;
import cn.appinfodb.service.Version.VersionService;
import cn.appinfodb.tools.Constants;
@RequestMapping("/info")
@Controller
public class InfoController {
	@Autowired
	InfoService infoService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	DataDictionaryService dataDictionaryService;
	@Autowired
	VersionService versionService;	
		//APP信息列表
		@RequestMapping(value="/applist")
		public String  applist(@RequestParam(required=false) String querySoftwareName,
											@RequestParam(required=false, defaultValue = "0") String queryFlatformId,
											@RequestParam(required=false , defaultValue="0") String queryCategoryLevel1,
											@RequestParam(required=false, defaultValue="0") String queryCategoryLevel2,
											@RequestParam(required=false, defaultValue = "0") String queryCategoryLevel3,
											@RequestParam(required=false, defaultValue="1") String pageIndex,
											HttpServletRequest request,
											HttpSession session){						
				List<Category>category1= categoryService.getCategOne(Integer.parseInt(queryCategoryLevel1));
				request.setAttribute("categoryLevel1List", category1);
									
				List<DataDictionary>  dataDictionarys=dataDictionaryService.getflatFoList();
				request.setAttribute("flatFormList", dataDictionarys);
				
				List<Info> infos=infoService.selectInfo(querySoftwareName,Integer.parseInt(queryFlatformId),Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3), Integer.parseInt(pageIndex), Constants.PAGE_SIZE);
				request.setAttribute("appInfoList", infos);
				request.setAttribute("querySoftwareName", querySoftwareName);
				
		
			session.setAttribute("categoryLevel1List", queryCategoryLevel1);
			session.setAttribute("categoryLevel2List", queryCategoryLevel2);
			session.setAttribute("categoryLevel3List", queryCategoryLevel3);
				
					int count=infoService.selectInfoCount(querySoftwareName, Integer.parseInt(queryFlatformId), Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3));
				request.setAttribute("totalCount", count);
				request.setAttribute("currentPageNo", pageIndex);
				int pageCount=count%Constants.PAGE_SIZE==0?count/Constants.PAGE_SIZE:count/Constants.PAGE_SIZE+1;
				request.setAttribute("totalPageCount", pageCount);
				
				return "backend/applist";
		}
		
		@RequestMapping(value="cate")
		@ResponseBody
		public Object fenlei( int pid){
			List<Category>category= categoryService.getCategTow(pid);
			return JSONArray.toJSONString(category);
		}	
		@RequestMapping(value="/checkApp",method=RequestMethod.GET)
		public String checkApp(int id,int vid,HttpServletRequest request){
			Info in=infoService.selectInfos(id, vid);
			request.setAttribute("appInfo", in);
			Version version=versionService.selectVersionById(in.getVersionId());
			request.setAttribute("appVersion", version);
			return "/backend/appcheck";
		}
		
		@RequestMapping(value="/checkApp",method=RequestMethod.POST)
		public String checkApp(@RequestParam(required=false)String id,@RequestParam(required=false)String status){
			if (status.equals("2")) {
				infoService.changeInfo(Integer.parseInt(status),Integer.parseInt(id));
				
			}else if(status.equals("3")){
				infoService.changeInfo(Integer.parseInt(status),Integer.parseInt(id));
			}
			return "redirect:/info/applist";
		}
}
