package cn.appinfodb.service.Category;

import java.util.List;


import cn.appinfodb.pojo.Category;

public interface CategoryService {
	public List<Category> selectCate1(int categoryLevel1);
	public List<Category> selectCate2(int categoryLevel2);
	public List<Category> selectCate3(int categoryLevel3);
	
	public List<Category> selectCa1();
	public List<Category> selectCa2();
	public List<Category> selectCa3();
	public List<Category>getCategOne();

	public List<Category>getCategTow(int firstListId);
	
	public List<Category>getCategThree(int secoundlistId);

}