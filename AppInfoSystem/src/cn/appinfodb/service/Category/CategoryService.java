package cn.appinfodb.service.Category;

import java.util.List;

import cn.appinfodb.pojo.Category;

public interface CategoryService {

	public List<Category>getCategOne(int parentId);
	/**
	 * 根据父类节点id实现分级查询信息
	 * @return
	 */
	public List<Category>getCategTow(int parentId);
	public List<Category>getCategoryThree(int parentId);
}
