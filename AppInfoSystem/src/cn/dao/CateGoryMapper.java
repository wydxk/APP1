package cn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_category;

public interface CateGoryMapper {
	public List<app_category> getCategoryOne(@Param("parentId")int parentId);
	public List<app_category> getCategoryTow(int parentId);
	public List<app_category> getCategoryThree(@Param("parentId")int parentId);
}
