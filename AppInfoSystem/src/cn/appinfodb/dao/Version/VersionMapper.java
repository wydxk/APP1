package cn.appinfodb.dao.Version;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.Version;

public interface VersionMapper {
		/**
		 * 根据id查询版本信息，后台
		 * @param id
		 * @return
		 */
		public Version selectVersionById(@Param("id")int id);
		/**
		 * 根据ID 查询历史版本列表
		*/
		public List<Version> getVersionById(@Param("appinfoId") int appinfoId);
		
		public List <Version> getVersionByInfoIdAndVid(@Param("appinfoId") int appinfoId,@Param("versionId") int versionId);
	
}
