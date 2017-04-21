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
			setObject(1, entity.getAccountnum());
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

	public int remove(String accountnum, int groupId) {
		if (initExecuteProc("{call pack_wxgroup.Remove(?,?,?)}")) {
			setObject(1, accountnum);
			setObject(2, groupId);
			registerOutParameter(3, Types.INTEGER);
			executeProc();
			int affect = (int) getObject(3);
			release();
			return affect;
		}
		return -1;
	}

	public int batchMoveToGroup(String accountnum, String openIds, int toGroupId) {
		if (initExecuteProc("{call pack_wxgroup.BatchMoveToGroup(?,?,?,?)}")) {
			setObject(1, accountnum);
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

	public WxGroup select(String accountnum, int groupid) {
		ResultSet rs = executeSql("select * from wxgroup where accountnum = ? and groupid = ?", new Object[] { accountnum, groupid });
		List<WxGroup> array = toList(rs);
		release();
		if (array == null || array.size() <= 0) {
			return null;
		}
		return array.get(0);
	}

	public List<WxGroup> selectByAccountnum() {
		return null;
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