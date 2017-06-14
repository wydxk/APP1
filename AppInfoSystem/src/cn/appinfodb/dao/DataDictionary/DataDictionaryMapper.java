package cn.appinfodb.dao.DataDictionary;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.DataDictionary;



import cn.appinfodb.pojo.DataDictionary;

public interface DataDictionaryMapper {
	public 	DataDictionary selectDataById(@Param("id")int id);
	public 	List<DataDictionary> selectData();
	/**
	 * 查询APP状态
	 * @return
	 */
	public List<DataDictionary> getStatus();
	
	/**
	 * 查询所属平台
	 * @return
	 */
	public List<DataDictionary> getflatFormList();
	

}
