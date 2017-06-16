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
	public List<Category> getCategOne(int parentId) {
		return categoryMapper.getCategoryOne(parentId);
	}
	@Override
	public List<Category> getCategTow(int parentId) {
		return categoryMapper.getCategoryTow(parentId);
	}
	@Override
	public List<Category> getCategoryThree(int parentId) {
		return categoryMapper.getCategoryThree(parentId);
	}

}
