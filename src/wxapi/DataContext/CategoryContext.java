package wxapi.DataContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.Entity.Category;

public class CategoryContext extends DBContextBase {

	public int insert(Category entity) {
		if (initExecuteProc("{call pack_category.insertorupdate(?,?,?,?,?)}")) {
			setObject(1, entity.getTitle());
			setObject(2, entity.getParentid());
			setObject(3, entity.getOrdernum());
			setObject(4, entity.getFlag());
			registerOutParameter(5, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(5);
			release();
			return affect;
		}
		return -1;
	}

	public int update(Category entity) {
		if (initExecuteProc("{call pack_category.updatedata(?,?,?,?,?)}")) {
			setObject(1, entity.getTitle());
			setObject(2, entity.getParentid());
			setObject(3, entity.getOrdernum());
			setObject(4, entity.getFlag());
			registerOutParameter(5, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(5);
			release();
			return affect;
		}
		return -1;
	}

	public List<Category> select(String flag) {
		ResultSet rs = executeSql("select * from category where isdeleted = 0 and flag = ? order by sort desc", new Object[] { flag });
		List<Category> array = toList(rs);
		release();
		return array;
	}

	public Category selectById(int id) {
		ResultSet rs = executeSql("select * from category where isdeleted = 0 and id = ?", new Object[] { id });
		List<Category> array = toList(rs);
		release();
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	public int delete(int id) {
		if (initExecuteProc("{call pack_category.remove(?,?)}")) {
			setObject(1, id);
			registerOutParameter(2, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(2);
			release();
			return affect;
		}
		return -1;
	}

	private List<Category> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		try {
			List<Category> array = new ArrayList<Category>();
			Category temp;
			while (rs.next()) {
				temp = new Category();
				temp.setId(rs.getInt("id"));
				temp.setTitle(rs.getString("title"));
				temp.setParentid(rs.getInt("parentid"));
				temp.setOrdernum(rs.getInt("ordernum"));
				temp.setSort(rs.getString("sort"));
				temp.setFlag(rs.getString("flag"));
				temp.setIsdeleted(rs.getBoolean("isdeleted"));
				array.add(temp);
			}
			return (array == null || array.size() == 0) ? null : array;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
