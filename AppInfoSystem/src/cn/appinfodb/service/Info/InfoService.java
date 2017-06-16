package cn.appinfodb.service.Info;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.Info;
public interface InfoService {
	
	public List<Info> selectInfo(String softwareName,
													int flatformId,
													int categoryLevel1,
													int categoryLevel2,
													int categoryLevel3,
													int cnindex,
													int pageCount);
	
	public int selectInfoCount(String softwareName,
			 int flatformId,
			 int categoryLevel1,
			 int categoryLevel2,
			 int categoryLevel3);	
	
	public Info selectInfos(int id,int versionId);
	
	public boolean changeInfo(int status,int id);
	
	public boolean addInfo(Info info);
	
	public boolean selectByAPK(String APKName);
	
	public List<Info> getInfos(String softwareName,
			int flatformId,
			int status,
			int categoryLevel1,
			int categoryLevel2,
			int categoryLevel3,
			int cnindex,
			int pageCount);
	
	public int getInfoCount(String softwareName,
			 int flatformId,
			 int status ,
			 int categoryLevel1,
			 int categoryLevel2,
			 int categoryLevel3);
	
	/*根据Id查询详情*/
	public Info  getInfById( int id);
	
	public Info selectInfoByInfoId(int id);		
}
