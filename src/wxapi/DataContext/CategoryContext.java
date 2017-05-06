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
import wxapi.Entity.Category;

public class CategoryContext extends DBContextBase {

	public int insert(Category entity) {
		Object object = executeProc("{call pack_category.insertdata(?,?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				Category entity = (Category) param[0];
				cs.setString(1, entity.getTitle());
				cs.setInt(2, entity.getParentid());
				cs.setInt(3, entity.getOrdernum());
				cs.setString(4, entity.getFlag());
				cs.setString(5, entity.getCvalue());
				cs.registerOutParameter(6, Types.INTEGER);
				return null;
			}
		}, new Object[] { entity }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(6);
				return affect;
			}
		}, null);
		if (object == null) {
			return -1;
		} else {
			return (int) object;
		}
	}

	public int update(Category entity) {
		Object object = executeProc("{call pack_category.updatedata(?,?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				Category entity = (Category) param[0];
				cs.setInt(1, entity.getId());
				cs.setString(2, entity.getTitle());
				cs.setInt(3, entity.getParentid());
				cs.setInt(4, entity.getOrdernum());
				cs.setString(5, entity.getCvalue());
				cs.registerOutParameter(6, Types.INTEGER);
				return null;
			}
		}, new Object[] { entity }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(6);
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
	public List<Category> select() {
		Object obj = executeSql("select * from category where isdeleted = 0  order by sort desc", null, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<Category>) obj;
	}

	@SuppressWarnings("unchecked")
	public List<Category> select(String flag) {
		Object obj = executeSql("select * from category where isdeleted = 0 and flag = ? order by sort desc", new Object[] { flag }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<Category>) obj;
	}

	@SuppressWarnings("unchecked")
	public Category selectById(int id) {
		Object obj = executeSql("select * from category where isdeleted = 0 and id = ?", new Object[] { id }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		List<Category> array = (List<Category>) obj;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	public int delete(int id) {
		Object object = executeProc("{call pack_category.remove(?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int id = (int) param[0];
				cs.setInt(1, id);
				cs.registerOutParameter(2, Types.INTEGER);
				return null;
			}
		}, new Object[] { id }, new ISQLOperate() {
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
				temp.setGrade(rs.getInt("grade"));
				temp.setSort(rs.getString("sort"));
				temp.setFlag(rs.getString("flag"));
				temp.setCvalue(rs.getString("cvalue"));
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
