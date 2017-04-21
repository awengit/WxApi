package wxapi.DataContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.Entity.CategoryFlag;

public class CategoryFlagContext extends DBContextBase {

	public int insertOrUpdate(CategoryFlag entity) {
		if (initExecuteProc("{call pack_categoryflag.insertorupdate(?,?,?,?)}")) {
			setObject(1, entity.getTitle());
			setObject(2, entity.getOrdernum());
			setObject(3, entity.getFlag());
			registerOutParameter(4, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(4);
			release();
			return affect;
		}
		return -1;
	}

	public int delete(String flag) {
		if (initExecuteProc("{call pack_categoryflag.remove(?,?)}")) {
			setObject(1, flag);
			registerOutParameter(2, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(2);
			release();
			return affect;
		}
		return -1;
	}

	public List<CategoryFlag> select() {
		ResultSet rs = executeSql("select * from categoryflag where isdeleted = 0", null);
		List<CategoryFlag> array = toList(rs);
		release();
		return array;
	}

	public CategoryFlag selectByFlag(String flag) {
		Object[] params = new Object[] { flag };
		ResultSet rs = executeSql("select * from categoryflag where isdeleted = 0 and flag=?", params);
		List<CategoryFlag> array = toList(rs);
		release();
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	private List<CategoryFlag> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		try {
			List<CategoryFlag> array = new ArrayList<CategoryFlag>();
			CategoryFlag temp;
			while (rs.next()) {
				temp = new CategoryFlag();
				temp.setTitle(rs.getString("title"));
				temp.setOrdernum(rs.getInt("ordernum"));
				temp.setIsdeleted(rs.getBoolean("isdeleted"));
				temp.setFlag(rs.getString("flag"));
				array.add(temp);
			}
			return (array == null || array.size() == 0) ? null : array;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
