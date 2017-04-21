package wxapi.DataService;

import java.util.List;
import wxapi.DataContext.CategoryContext;
import wxapi.Entity.Category;

public class CategoryService {

	private CategoryContext context = new CategoryContext();

	public int insert(Category entity) {
		return context.insert(entity);
	}

	public int update(Category entity) {
		return context.update(entity);
	}

	public List<Category> select(String flag) {
		return context.select(flag);
	}

	public Category selectById(int id) {
		return context.selectById(id);
	}

	public int delete(int id) {
		return context.delete(id);
	}

}
