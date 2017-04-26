package wxapi.DataContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wxapi.DataContext.Base.DBContextBase;
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

	public List<LoginUser> select() {
		ResultSet rs = executeSql("select * from loginuser where isdeleted=0", null);
		List<LoginUser> array = toList(rs);
		release();
		return array;
	}

	public LoginUser select(String loginname) {
		ResultSet rs = executeSql("select * from loginuser where loginname=upper(?) and isdeleted=0", new Object[] { loginname });
		List<LoginUser> array = toList(rs);
		release();
		if (array == null || array.size() == 0) {
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
