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
import wxapi.Entity.LoginUser;

public class LoginUserContext extends DBContextBase {

	public int insertOrUpdate(LoginUser entity) {
		Object object = executeProc("{call pack_loginuser.InsertOrUpdate(?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				LoginUser entity = (LoginUser) param[0];
				cs.setString(1, entity.getLoginname());
				cs.setString(2, entity.getLoginpsw());
				cs.setString(3, entity.getRolecode());
				cs.setString(4, entity.getWxaccountcodes());
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

	public int updatePsw(String loginName, String oldPsw, String newPsw) {
		Object[] params = new Object[] { newPsw, loginName, oldPsw };
		return executeSqlNonQuery("update loginuser set loginpsw=? where loginname=upper(?) and loginpsw and isdeleted=0", params);
	}

	public int delete(String loginName) {
		Object[] params = new Object[] { loginName };
		return executeSqlNonQuery("update loginuser set isdeleted=1 where loginname=upper(?) and isdeleted=0", params);
	}

	@SuppressWarnings("unchecked")
	public List<wxapi.Entity.View.LoginUser> select() {
		Object obj = executeSql("select a.*,b.title userroletitle,b.userrights from loginuser a left join userrole b on a.rolecode=b.rolecode where isdeleted=0", null, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<wxapi.Entity.View.LoginUser>) obj;
	}

	@SuppressWarnings("unchecked")
	public wxapi.Entity.View.LoginUser select(String loginname) {
		Object obj = executeSql("select a.*,b.title userroletitle,b.userrights from loginuser a left join userrole b on a.rolecode=b.rolecode where loginname=upper(?) and isdeleted=0", new Object[] { loginname }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		List<wxapi.Entity.View.LoginUser> array = (List<wxapi.Entity.View.LoginUser>) obj;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	@SuppressWarnings("unchecked")
	public wxapi.Entity.View.LoginUser select(String loginname, String loginPsw) {
		Object obj = executeSql("select a.*,b.title userroletitle,b.userrights from loginuser a left join userrole b on a.rolecode=b.rolecode where loginname=upper(?) and loginPsw=? and isdeleted=0", new Object[] { loginname, loginPsw }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		List<wxapi.Entity.View.LoginUser> array = (List<wxapi.Entity.View.LoginUser>) obj;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	private List<wxapi.Entity.View.LoginUser> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<wxapi.Entity.View.LoginUser> array = new ArrayList<wxapi.Entity.View.LoginUser>();
		try {
			wxapi.Entity.View.LoginUser entity;
			while (rs.next()) {
				entity = new wxapi.Entity.View.LoginUser();
				entity.setUserroletitle(rs.getString("userroletitle"));
				entity.setUserrights(rs.getString("userrights"));
				entity.setLoginname(rs.getString("loginname"));
				entity.setLoginpsw(rs.getString("loginpsw"));
				entity.setRolecode(rs.getString("rolecode"));
				entity.setWxaccountcodes(rs.getString("wxaccountcodes"));
				entity.setIsdeleted(rs.getInt("isdeleted") > 0 ? true : false);
				array.add(entity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (array == null || array.size() == 0) ? null : array;
	}
}
