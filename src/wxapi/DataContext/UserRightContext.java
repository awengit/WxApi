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
import wxapi.Entity.UserRight;
import wxapi.Entity.UserRole;

public class UserRightContext extends DBContextBase {

	public int insertRight(UserRight entity) {
		Object[] params = new Object[] { entity.getCategoryid(), entity.getTitle(), entity.getUrl().toLowerCase(), entity.getDisplayinbanner(), entity.getOrdernum() };
		return executeSqlNonQuery("insert into userright(categoryid,title,url,displayinbanner,ordernum) values (?,?,?,?,?)", params);
	}

	public int updateRight(UserRight entity) {
		Object[] params = new Object[] { entity.getCategoryid(), entity.getTitle(), entity.getUrl().toLowerCase(), entity.getDisplayinbanner(), entity.getOrdernum(), entity.getId() };
		return executeSqlNonQuery("update userright set categoryid=?,title=?,url=?,displayinbanner=?,ordernum=? where id=?", params);
	}

	public int deleteRight(int id) {
		return executeSqlNonQuery("update userright set isdeleted=1 where id=?", new Object[] { id });
	}

	@SuppressWarnings("unchecked")
	public List<wxapi.Entity.View.UserRight> selectUserRight() {
		Object object = executeSql("select a.*,b.title rightgrouptitle from userright a left join category b on a.categoryid=b.id where a.isdeleted=0 order by b.sort desc,a.ordernum desc", null, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toRightList(rs);
			}
		});
		return (List<wxapi.Entity.View.UserRight>) object;
	}

	@SuppressWarnings("unchecked")
	public wxapi.Entity.View.UserRight selectRightById(int id) {
		Object obj = executeSql("select a.*,b.title rightgrouptitle from userright a left join category b on a.categoryid=b.id where a.isdeleted=0 and a.id=?", new Object[] { id }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toRightList(rs);
			}
		});
		List<wxapi.Entity.View.UserRight> array = (List<wxapi.Entity.View.UserRight>) obj;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<wxapi.Entity.View.UserRight> selectRightByCategoryId(int id) {
		Object object = executeSql("select a.*,b.title rightgrouptitle from userright a left join category b on a.categoryid=b.id where a.isdeleted=0 and a.categoryid=? order by b.sort desc,a.ordernum desc", new Object[] { id }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toRightList(rs);
			}
		});
		return (List<wxapi.Entity.View.UserRight>) object;
	}

	private List<wxapi.Entity.View.UserRight> toRightList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<wxapi.Entity.View.UserRight> array = new ArrayList<wxapi.Entity.View.UserRight>();
		wxapi.Entity.View.UserRight right;
		try {
			while (rs.next()) {
				right = new wxapi.Entity.View.UserRight();
				right.setRightgrouptitle(rs.getString("rightgrouptitle"));
				right.setId(rs.getInt("id"));
				right.setCategoryid(rs.getInt("categoryid"));
				right.setTitle(rs.getString("title"));
				right.setUrl(rs.getString("url"));
				right.setDisplayinbanner(rs.getInt("displayinbanner") > 0 ? true : false);
				right.setOrdernum(rs.getInt("ordernum"));
				right.setIsdeleted(rs.getInt("isdeleted") > 0 ? true : false);
				array.add(right);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (array == null || array.size() == 0) ? null : array;
	}

	public int insertOrUpdateUserRole(UserRole entity) {
		Object object = executeProc("{call pack_userright.InsertOrUpdateUserRole(?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				UserRole entity = (UserRole) param[0];
				cs.setString(1, entity.getTitle());
				cs.setString(2, entity.getUserrights());
				cs.setInt(3, entity.getOrdernum());
				cs.setString(4, entity.getRolecode().toUpperCase());
				cs.registerOutParameter(5, Types.INTEGER);
				return null;
			}
		}, new Object[] { entity }, new ISQLOperate() {
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(5);
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
	public UserRole selectUserRoleByCode(String rolecode) {
		Object object = executeSql("select * from userrole where rolecode=?", new Object[] { rolecode }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toRoleList(rs);
			}
		});
		List<UserRole> array = (List<UserRole>) object;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> selectUserRole() {
		Object object = executeSql("select * from userrole order by ordernum desc", null, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toRoleList(rs);
			}
		});
		return (List<UserRole>) object;
	}

	private List<UserRole> toRoleList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<UserRole> array = new ArrayList<UserRole>();
		UserRole entity;
		try {
			while (rs.next()) {
				entity = new UserRole();
				entity.setOrdernum(rs.getInt("ordernum"));
				entity.setRolecode(rs.getString("rolecode"));
				entity.setTitle(rs.getString("title"));
				entity.setUserrights(rs.getString("userrights"));
				array.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (array == null || array.size() == 0) ? null : array;
	}
}
