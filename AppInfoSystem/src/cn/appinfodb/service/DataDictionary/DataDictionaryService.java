package cn.appinfodb.service.DataDictionary;

import java.util.List;
import cn.appinfodb.pojo.DataDictionary;

public interface DataDictionaryService {
	
	public 	DataDictionary selectDataById(int id);
	
	public List<DataDictionary> selectDataBytypeCode(String typeCode);
	
	public List<DataDictionary> getStatuss();

	public List<DataDictionary> getflatFoList();
	
}
