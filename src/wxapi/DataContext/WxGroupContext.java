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
import wxapi.Entity.WxGroup;

public class WxGroupContext extends DBContextBase {

	public int insertOrUpdate(WxGroup entity) {
		Object object = executeProc("{call pack_wxgroup.insertOrUpdate(?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				WxGroup entity = (WxGroup) param[0];
				cs.setString(1, entity.getAccountcode());
				cs.setInt(2, entity.getGroupid());
				cs.setString(3, entity.getGroupname());
				cs.setInt(4, entity.getUsercount());
				cs.registerOutParameter(5, Types.INTEGER);
				return null;
			}
		}, new Object[] { entity }, new ISQLOperate() {
			@Override
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

	public int batchInsert(List<wxapi.Entity.Wx.WxGroup> array, String accountnum) {
		if (array == null || array.size() == 0) {
			return 0;
		}
		executeSqlNonQuery("delete wxgroup where accountnum = ?", new Object[] { accountnum });
		executeSqlByBatch("insert into wxgroup(accountnum,groupid,groupname,usercount)values(?,?,?,?)", new Object[] { array, accountnum }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				@SuppressWarnings("unchecked")
				List<wxapi.Entity.Wx.WxGroup> array = (List<wxapi.Entity.Wx.WxGroup>) param[0];
				String accountnum = (String) param[1];
				for (int i = 0; i < array.size(); i++) {
					ps.setObject(1, accountnum);
					ps.setObject(2, array.get(i).getId());
					ps.setObject(3, array.get(i).getName());
					ps.setObject(4, array.get(i).getCount());
					ps.addBatch();
					if ((i + 1) % 100 == 0 || i == array.size() - 1) {
						ps.executeBatch();
					}
				}
				return null;
			}
		});
		return 0;
	}

	public int remove(String accountcode, int groupId) {
		Object object = executeProc("{call pack_wxgroup.insertOrUpdate(?,?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				String accountcode = (String) param[0];
				int groupId = (int) param[1];
				cs.setString(1, accountcode);
				cs.setInt(2, groupId);
				cs.registerOutParameter(3, Types.INTEGER);
				return null;
			}
		}, new Object[] { accountcode, groupId }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				int affect = cs.getInt(3);
				return affect;
			}
		}, null);
		if (object == null) {
			return -1;
		} else {
			return (int) object;
		}
	}

	public int batchMoveToGroup(String accountcode, String openIds, int toGroupId) {
		Object object = executeProc("{call pack_wxgroup.BatchMoveToGroup(?,?,?,?)}", new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				String accountcode = (String) param[0];
				String openIds = (String) param[1];
				int toGroupId = (int) param[2];
				cs.setString(1, accountcode);
				cs.setString(2, openIds);
				cs.setInt(3, toGroupId);
				cs.registerOutParameter(4, Types.INTEGER);
				return null;
			}
		}, new Object[] { accountcode, openIds, toGroupId }, new ISQLOperate() {
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

	@SuppressWarnings("unchecked")
	public WxGroup select(String accountcode, int groupid) {
		Object obj = executeSql("select a.accountname as accountname,a.accountcode as accountcode,b.* from officialaccount a left join wxgroup b on a.accountnum=b.accountnum where a.accountcode = ? and a.isdeleted = 0 and b.groupid = ?", new Object[] { accountcode, groupid }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		List<WxGroup> array = (List<WxGroup>) obj;
		if (array == null) {
			return null;
		}
		return array.get(0);
	}

	@SuppressWarnings("unchecked")
	public List<WxGroup> selectByAccountcode(String accountcode) {
		Object obj = executeSql("select a.accountname as accountname,a.accountcode as accountcode,b.* from officialaccount a left join wxgroup b on a.accountnum=b.accountnum where a.accountcode = upper(?) and a.isdeleted = 0", new Object[] { accountcode }, new ISQLOperate() {
			@Override
			public Object operate(Object[] param, Connection conn, PreparedStatement ps, CallableStatement cs, ResultSet rs) throws SQLException {
				return toList(rs);
			}
		});
		return (List<WxGroup>) obj;
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