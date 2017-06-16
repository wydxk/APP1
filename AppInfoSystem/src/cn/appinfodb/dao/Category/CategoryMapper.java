package cn.appinfodb.dao.Category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.Category;
public interface CategoryMapper {
	
	/**
	 * 根据父类节点id实现分级查询信息
	 * @return
	 */
	public List<Category>getCategoryOne(@Param("parentId")int parentId);
	
	public List<Category>getCategoryTow(@Param("parentId")int parentId);
	
	public List<Category>getCategoryThree(@Param("parentId")int parentId);
	
}
