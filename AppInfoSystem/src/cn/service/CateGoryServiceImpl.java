package cn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dao.CateGoryMapper;
import cn.pojo.app_category;
@Service("cateGoryService")
public class CateGoryServiceImpl implements CateGoryService{
	@Autowired
	public CateGoryMapper catecorymapper;
	
	public void setCatecorycapper(CateGoryMapper catecorycapper) {
		this.catecorymapper = catecorycapper;
	}

	@Override
	public List<app_category> getcategory1(int parentId) {
		// TODO 自动生成的方法存根
		return catecorymapper.getCategoryOne(parentId);
	}

	@Override
	public List<app_category> getCategory2(int parentId) {
		// TODO 自动生成的方法存根
		return catecorymapper.getCategoryTow(parentId);
	}

	@Override
	public List<app_category> getCategory3(int parentId) {
		// TODO 自动生成的方法存根
		return catecorymapper.getCategoryThree(parentId);
	}

}
