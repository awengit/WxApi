package wxapi.DataService;

import java.util.List;
import wxapi.DataContext.CategoryFlagContext;
import wxapi.Entity.CategoryFlag;

public class CategoryFlagService {

	private CategoryFlagContext context = new CategoryFlagContext();

	public int insertOrUpdate(CategoryFlag entity) {
		return context.insertOrUpdate(entity);
	}

	public int delete(String flag) {
		return context.delete(flag);
	}

	public List<CategoryFlag> select() {
		return context.select();
	}

	public CategoryFlag selectByFlag(String flag) {
		return context.selectByFlag(flag);
	}

}