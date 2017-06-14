package cn.appinfodb.service.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.appinfodb.dao.Category.CategoryMapper;
import cn.appinfodb.pojo.Category;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;


	@Override
	public List<Category> selectCate1(int categoryLevel1) {
		return categoryMapper.selectCate1(categoryLevel1);
	}

	@Override
	public List<Category> selectCate2(int categoryLevel2) {
		return categoryMapper.selectCate2(categoryLevel2);
	}

	@Override
	public List<Category> selectCate3(int categoryLevel3) {
		return categoryMapper.selectCate3(categoryLevel3);
	}

	@Override
	public List<Category> selectCa1() {
		// TODO Auto-generated method stub
		return categoryMapper.selectCa1();
	}

	@Override
	public List<Category> selectCa2() {
		// TODO Auto-generated method stub
		return categoryMapper.selectCa2();
	}

	@Override
	public List<Category> selectCa3() {
		// TODO Auto-generated method stub
		return categoryMapper.selectCa3();
	}
	

	
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}


	@Override
	public List<Category> getCategOne() {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryOne();
	}


	@Override
	public List<Category> getCategTow(int firstListId) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryTow(firstListId);
	}


	@Override
	public List<Category> getCategThree(int secoundlistId) {
		// TODO Auto-generated method stub
		return categoryMapper.getCategoryThree(secoundlistId);
	}

}
