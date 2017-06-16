package cn.appinfodb.service.Version;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.Version.VersionMapper;
import cn.appinfodb.pojo.Version;

@Service("versionService")
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	VersionMapper versionMapper;
	
	
	
	public void setVersionMapper(VersionMapper versionMapper) {
		this.versionMapper = versionMapper;
	}

	@Override
	public List<Version> getVerById(int id) {
		return versionMapper.getVersionById(id);
	}

	@Override
	public List<Version> getVersionByInfoIdAndVersionid(int appinfoId,
			int versionId) {
		// TODO Auto-generated method stub
		return versionMapper.getVersionByInfoIdAndVid( versionId,appinfoId);
	}

	@Override
	public Version getVersionByInIdAndVerid( int versionId,int appinfoId) {
		// TODO Auto-generated method stub
		return versionMapper.getVersionByInfAndVid(versionId, appinfoId);
	}

	@Override
	public Version selectVersionByAid( int id) {
		// TODO Auto-generated method stub
		return versionMapper.selectVersionByAid(id);
	}

	@Override
	public List<Version> selectVersion(int appId) {
		// TODO Auto-generated method stub
		return versionMapper.selectVersion(appId);
	}

	@Override
	public boolean changeVersion(Version version) {
		int row=versionMapper.changeVersion(version);
		if(row==1){
			return true;
		}else{
			return  false;
		}
	}

	@Override
	public boolean addVersion(Version version) {
		int row=versionMapper.addVersion(version);
		if(row==1){
			return true;
		}else{
			return  false;
		}
	}
	
	public int selectId(){
		return versionMapper.selectId();
	}

}
