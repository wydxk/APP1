package cn.appinfodb.service.Version;

import java.util.List;

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

}
