package cn.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.app_info;

public interface App_infoService {
	public List<app_info> getAllApp(String softwareName,int status,int flatformId,int curreIndex,int pageSize);
	public int getCountByConcent(String softwareName,int status,int flatformId);
}
