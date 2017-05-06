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
import wxapi.Entity.OfficialAccount;

public class OfficialAccountContext extends DBContextBase {

	public int insertOrUpdate(OfficialAccount entity) {
		Object object = executeProc("{call pack_officialaccount.InsertOrUpdate(?,?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				OfficialAccount entity = (OfficialAccount) param[0];
				cs.setString(1, entity.getAccountcode());
				cs.setString(2, entity.getAccountname());
				cs.setString(3, entity.getAccountnum());
				cs.setString(4, entity.getAppid());
				cs.setString(5, entity.getSecret());
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

	public int remove(String accountcode) {
		Object object = executeProc("{call pack_officialaccount.Remove(?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				String accountcode = (String) param[0];
				cs.setString(1, accountcode);
				cs.registerOutParameter(2, Types.INTEGER);
				return null;
			}
		}, new Object[] { accountcode }, new ISQLOperate() {
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
	public OfficialAccount selectByAccountCode(String accountcode) {
		Object[] params = new Object[] { accountcode };
		Object obj = executeSql("select * from officialaccount where isdeleted = 0 and accountcode=upper(?)", params, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		List<OfficialAccount> array = (List<OfficialAccount>) obj;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<OfficialAccount> select() {
		Object obj = executeSql("select * from officialaccount where isdeleted = 0", null, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<OfficialAccount>) obj;
	}

	private List<OfficialAccount> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<OfficialAccount> array = new ArrayList<OfficialAccount>();
		OfficialAccount account;
		try {
			while (rs.next()) {
				account = new OfficialAccount();
				account.setAccountcode(rs.getString("accountcode"));
				account.setAccountname(rs.getString("accountname"));
				account.setAccountnum(rs.getString("accountnum"));
				account.setAppid((rs.getString("appid")));
				account.setSecret((rs.getString("secret")));
				account.setIsdeleted(rs.getInt("isdeleted") > 0 ? true : false);
				array.add(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (array == null || array.size() <= 0) ? null : array;
	}
}
