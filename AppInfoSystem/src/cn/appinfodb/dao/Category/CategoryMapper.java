package cn.appinfodb.dao.Category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.Category;

public interface CategoryMapper {


	
	public List<Category> selectCate1(@Param("categoryLevel1")int categoryLevel1);
	public List<Category> selectCate2(@Param("categoryLevel2")int categoryLevel2);
	public List<Category> selectCate3(@Param("categoryLevel3")int categoryLevel3);
	
	public List<Category> selectCa1();
	public List<Category> selectCa2();
	public List<Category> selectCa3();
	
	

	public List<Category>getCategoryOne(@Param("parentId")int parentId);
	
	public List<Category>getCategoryTow(int parentId);
	
	public List<Category>getCategoryThree(@Param("parentId")int parentId);

}
