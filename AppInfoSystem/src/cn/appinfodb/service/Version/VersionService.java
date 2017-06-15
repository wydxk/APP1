package cn.appinfodb.service.Version;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import cn.appinfodb.pojo.Version;

public interface VersionService {

	/**
	 * 根据ID 查询历史版本列表
	*/
	public List<Version> getVerById(int id);
	
	
	public List <Version> getVersionByInfoIdAndVersionid( int appinfoId, int versionId);

	
}
