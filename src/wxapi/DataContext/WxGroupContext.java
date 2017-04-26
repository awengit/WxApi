package wxapi.DataContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import wxapi.DataContext.Base.DBContextBase;
import wxapi.Entity.WxGroup;

public class WxGroupContext extends DBContextBase {

	public int insertOrUpdate(WxGroup entity) {
		if (initExecuteProc("{call pack_wxgroup.insertOrUpdate(?,?,?,?,?)}")) {
			setObject(1, entity.getAccountcode());
			setObject(2, entity.getGroupid());
			setObject(3, entity.getGroupname());
			setObject(4, entity.getUsercount());
			registerOutParameter(5, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(5);
			release();
			return affect;
		}
		return -1;
	}

	public int batchInsert(List<wxapi.Entity.Wx.WxGroup> array, String accountnum) {
		if (array == null || array.size() == 0) {
			return 0;
		}
		Object[][] data = new Object[array.size()][4];
		for (int i = 0; i < array.size(); i++) {
			data[i][0] = accountnum;
			data[i][1] = array.get(i).getId();
			data[i][2] = array.get(i).getName();
			data[i][3] = array.get(i).getCount();
		}
		executeSqlNonQuery("delete wxgroup where accountnum = ?", new Object[] { accountnum });
		int[] result = executeSqlByBatch("insert into wxgroup(accountnum,groupid,groupname,usercount)values(?,?,?,?)", data);
		if (result == null) {
			return 0;
		}
		int affect = 0;
		for (int i : result) {
			affect += i;
		}
		return affect;
	}

	public int remove(String accountcode, int groupId) {
		if (initExecuteProc("{call pack_wxgroup.Remove(?,?,?)}")) {
			setObject(1, accountcode);
			setObject(2, groupId);
			registerOutParameter(3, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(3);
			release();
			return affect;
		}
		return -1;
	}

	public int batchMoveToGroup(String accountcode, String openIds, int toGroupId) {
		if (initExecuteProc("{call pack_wxgroup.BatchMoveToGroup(?,?,?,?)}")) {
			setObject(1, accountcode);
			setObject(2, openIds);
			setObject(3, toGroupId);
			registerOutParameter(4, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(4);
			release();
			return affect;
		}
		return -1;
	}

	public WxGroup select(String accountcode, int groupid) {
		ResultSet rs = executeSql("select a.accountname as accountname,a.accountcode as accountcode,b.* from officialaccount a left join wxgroup b on a.accountnum=b.accountnum where a.accountcode = ? and a.isdeleted = 0 and b.groupid = ?", new Object[] { accountcode, groupid });
		List<WxGroup> array = toList(rs);
		release();
		if (array == null || array.size() <= 0) {
			return null;
		}
		return array.get(0);
	}

	public List<WxGroup> selectByAccountcode(String accountcode) {
		ResultSet rs = executeSql("select a.accountname as accountname,a.accountcode as accountcode,b.* from officialaccount a left join wxgroup b on a.accountnum=b.accountnum where a.accountcode = upper(?) and a.isdeleted = 0", new Object[] { accountcode });
		List<WxGroup> array = toList(rs);
		release();
		return array;
	}

	private List<WxGroup> toList(ResultSet rs) {
		if (rs == null) {
			return null;
		}
		List<WxGroup> array = new ArrayList<WxGroup>();
		WxGroup group;
		try {
			while (rs.next()) {
				group = new WxGroup();
				group.setAccountcode(rs.getString("accountcode"));
				group.setAccountname(rs.getString("accountname"));
				group.setAccountnum(rs.getString("accountnum"));
				group.setGroupid(rs.getInt("groupid"));
				group.setGroupname(rs.getString("groupname"));
				group.setUsercount(rs.getInt("usercount"));
				array.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (array == null || array.size() <= 0) ? null : array;
	}

}