package wxapi.DataContext;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.DataContext.Base.ISQLOperate;
import wxapi.Entity.CategoryFlag;

public class CategoryFlagContext extends DBContextBase {

	public int insertOrUpdate(CategoryFlag entity) {
		Object object = executeProc("{call pack_categoryflag.insertorupdate(?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				CategoryFlag entity = (CategoryFlag) param[0];
				cs.setString(1, entity.getTitle());
				cs.setInt(2, entity.getOrdernum());
				cs.setString(3, entity.getFlag());
				cs.registerOutParameter(4, Types.INTEGER);
				return null;
			}
		}, new Object[] { entity }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(4);
				return affect;
			}
		}, null);
		if (object == null) {
			return -1;
		} else {
			return (int) object;
		}
	}

	public int delete(String flag) {
		Object object = executeProc("{call pack_categoryflag.remove(?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				String flag = (String) param[0];
				cs.setString(1, flag);
				cs.registerOutParameter(2, Types.INTEGER);
				return null;
			}
		}, new Object[] { flag }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(2);
				return affect;
			}
		}, null);
		if (object == null) {
			return -1;
		} else {
			return (int) object;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CategoryFlag> select() {
		Object obj = executeSql("select * from categoryflag where isdeleted = 0 order by ordernum desc", null, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<CategoryFlag>) obj;
	}

	@SuppressWarnings("unchecked")
	public CategoryFlag selectByFlag(String flag) {
		Object[] params = new Object[] { flag };
		Object obj = executeSql("select * from categoryflag where isdeleted = 0 and flag=?", params, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		List<CategoryFlag> array = (List<CategoryFlag>) obj;
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
