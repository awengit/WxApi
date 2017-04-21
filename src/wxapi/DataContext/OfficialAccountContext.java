package wxapi.DataContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.Entity.OfficialAccount;

public class OfficialAccountContext extends DBContextBase {

	public int insertOrUpdate(OfficialAccount entity) {
		if (initExecuteProc("{call pack_officialaccount.InsertOrUpdate(?,?,?,?,?)}")) {
			setObject(1, entity.getAccountname());
			setObject(2, entity.getAccountnum());
			setObject(3, entity.getAppid());
			setObject(4, entity.getSecret());
			registerOutParameter(5, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(5);
			release();
			return affect;
		}
		return -1;
	}

	public int remove(String accountnum) {
		if (initExecuteProc("{call pack_officialaccount.Remove(?,?)}")) {
			setObject(1, accountnum);
			registerOutParameter(2, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(2);
			release();
			return affect;
		}
		return -1;
	}

	public OfficialAccount selectByAccountNum(String accountnum) {
		Object[] params = new Object[] { accountnum };
		ResultSet rs = executeSql("select * from officialaccount where isdeleted = 0 and accountnum=?", params);
		List<OfficialAccount> array = toList(rs);
		release();
		if (array == null || array.size() <= 0) {
			return null;
		}
		return array.get(0);
	}

	public List<OfficialAccount> select() {
		ResultSet rs = executeSql("select * from officialaccount where isdeleted = 0", null);
		List<OfficialAccount> array = toList(rs);
		release();
		return array;
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
