package cn.appinfodb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import cn.appinfodb.service.Category.CategoryService;
import cn.appinfodb.service.DataDictionary.DataDictionaryService;
import cn.appinfodb.service.Info.InfoService;
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
	//APP管理
		/*@RequestMapping(value="/applist",method=RequestMethod.GET)
		public String  applist(){
			return "backend/applist";
		}*/
		
		//APP信息列表
		@RequestMapping(value="/applist")
		public String  applist(@RequestParam(required=false) String querySoftwareName,
											@RequestParam(required=false) String queryFlatformId,
											@RequestParam(required=false) String queryCategoryLevel1,
											@RequestParam(required=false) String queryCategoryLevel2,
											@RequestParam(required=false) String queryCategoryLevel3,
											@RequestParam(required=false) String pageIndex,
											HttpServletRequest request){
				System.out.println("aaaaaaaaaaaaaaaa");
				
				if (queryFlatformId==null ||queryFlatformId=="") {
					queryFlatformId="0";
				}
				if (queryCategoryLevel1==null ||queryCategoryLevel1=="") {
					queryCategoryLevel1="0";
				}
				if (queryCategoryLevel2==null ||queryCategoryLevel2=="") {
					queryCategoryLevel2="0";
				}
				if (queryCategoryLevel3==null ||queryCategoryLevel3=="") {
					queryCategoryLevel3="0";
				}
				if (pageIndex==null) {
					pageIndex="1";
				}
				List<Category>category1= categoryService.selectCa1();
				request.setAttribute("categoryLevel1List", category1);
				
				List<Category>category2= categoryService.selectCa2();
				request.setAttribute("categoryLevel2List", category2);
				
				List<Category>category3= categoryService.selectCa3();
				request.setAttribute("categoryLevel3List", category3);
				
				List<DataDictionary>  dataDictionarys=dataDictionaryService.selectData();
				request.setAttribute("flatFormList", dataDictionarys);
				System.out.println(dataDictionarys.get(0).getValueId());
				
				List<Info> infos=infoService.selectInfo(querySoftwareName,Integer.parseInt(queryFlatformId),Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3), Integer.parseInt(pageIndex), Constants.PAGE_SIZE);
				request.setAttribute("appInfoList", infos);
			
				request.setAttribute("querySoftwareName", querySoftwareName);
				
				/*request.setAttribute("categoryLevel1List", queryCategoryLevel1);
				request.setAttribute("categoryLevel2List", queryCategoryLevel2);
				request.setAttribute("categoryLevel3List", queryCategoryLevel3);*/
				
				int count=infoService.selectInfoCount(querySoftwareName, Integer.parseInt(queryFlatformId), Integer.parseInt(queryCategoryLevel1), Integer.parseInt(queryCategoryLevel2), Integer.parseInt(queryCategoryLevel3));
				request.setAttribute("totalCount", count);
				request.setAttribute("currentPageNo", pageIndex);
				int pageCount=count%Constants.PAGE_SIZE==0?count/Constants.PAGE_SIZE:count/Constants.PAGE_SIZE+1;
				request.setAttribute("totalPageCount", pageCount);
				return "backend/applist";
		}
		
		@RequestMapping(value="cate1")
		@ResponseBody
		public Object fenlei1(String pid){
			
			List<Category>category1= categoryService.selectCate1(Integer.parseInt(pid));
			return JSONArray.toJSONString(category1);
		}
		@RequestMapping(value="cate2")
		@ResponseBody
		public Object fenlei2(String pid){
			List<Category>category2= categoryService.selectCate2(Integer.parseInt(pid));
			return JSONArray.toJSONString(category2);
		}
}
