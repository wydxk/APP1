package cn.appinfodb.service.Version;
import java.util.List;

import cn.appinfodb.pojo.Version;

public interface VersionService {
	/**
	 * 根据ID 查询最新版本信息
	*/
	public Version selectVersionById(int id);
	
	/**
	 * 根据ID 查询历史版本列表
	*/
	public List<Version> getVerById(int id);
	
	
	public List <Version> getVersionByInfoIdAndVersionid( int appinfoId, int versionId);

}
