package cn.appinfodb.service.Info;

import java.util.List;
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
	
	public List<Info> selectFeClass();
	
	public List<Info> getInfos(String softwareName ,
			int flatformId ,
			int status ,
			int categoryLevel1 ,
			int categoryLevel2 ,
			int categoryLevel3);
	
	
}





	

/**/ 
