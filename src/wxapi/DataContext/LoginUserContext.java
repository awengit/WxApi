package wxapi.DataContext;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.DataContext.Base.ISQLOperate;
import wxapi.Entity.LoginUser;

public class LoginUserContext extends DBContextBase {

	public int insert(LoginUser entity) {
		Object[] params = new Object[] { entity.getLoginname(), entity.getLoginpsw(), entity.getRolecode(), entity.getWxaccountcodes() };
		return executeSqlNonQuery("insert into loginuser (loginname,loginpsw,rolecode,wxaccountcodes) values (upper(?),?,?,?)", params);
	}

	public int update(LoginUser entity) {
		Object[] params = new Object[] { entity.getLoginname(), entity.getRolecode(), entity.getWxaccountcodes() };
		return executeSqlNonQuery("update loginuser set rolecode=?,wxaccountcodes=? where loginname=upper(?) and isdeleted=0", params);
	}

	@SuppressWarnings("unchecked")
	public List<LoginUser> select() {
		Object obj = executeSql("select * from loginuser where isdeleted=0", null, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<LoginUser>) obj;
	}

	@SuppressWarnings("unchecked")
	public LoginUser select(String loginname) {
		Object obj = executeSql("select * from loginuser where loginname=upper(?) and isdeleted=0", new Object[] { loginname }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		List<LoginUser> array = (List<LoginUser>) obj;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	private List<LoginUser> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<LoginUser> array = new ArrayList<LoginUser>();
		try {
			LoginUser entity;
			while (rs.next()) {
				entity = new LoginUser();
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
