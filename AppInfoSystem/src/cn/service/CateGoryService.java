package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_category;

public interface CateGoryService {
	public List<app_category> getcategory1(int parentId);
	public List<app_category> getCategory2(int parentId);
	public List<app_category> getCategory3(int parentId);
}
