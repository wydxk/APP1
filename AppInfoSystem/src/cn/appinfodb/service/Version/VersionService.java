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

	public Version getVersionByInIdAndVerid( int versionId,int appinfoId);
	
	public Version selectVersionByAid( int id);
	public List<Version> selectVersion( int appId);

	public boolean changeVersion(Version version);
	
	public boolean addVersion(Version version);
	
	 public int selectId();
	
	
}
